package com.jzg.jzgoto.phone.tools.view.swipemenulistview;

import com.jzg.jzgoto.phone.components.self.discover.xlistview.XListViewFooter;
import com.jzg.jzgoto.phone.components.self.discover.xlistview.XListViewHeader;
import com.zheng.listviewdemo.R;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

/**
 * 
 * 
 */
public class SwipeMenuListView extends ListView implements OnScrollListener{

	private static final int TOUCH_STATE_NONE = 0;
	private static final int TOUCH_STATE_X = 1;
	private static final int TOUCH_STATE_Y = 2;

	private int MAX_Y = 5;
	private int MAX_X = 3;
	private float mDownX;
	private float mDownY;
	private int mTouchState;
	private int mTouchPosition;
	private SwipeMenuLayout mTouchView;

	private SwipeMenuCreator mMenuCreator;
	private OnMenuItemClickListener mOnMenuItemClickListener;
	private Interpolator mCloseInterpolator;
	private Interpolator mOpenInterpolator;
	
	
	private float mLastY = -1; // save event y
	private Scroller mScroller; // used for scroll back
	private OnScrollListener mScrollListener; // user's scroll listener

	// the interface to trigger refresh and load more.
	private IXListViewListener mListViewListener;

	// -- header view
	private XListViewHeader mHeaderView;
	// header view content, use it to calculate the Header's height. And hide it
	// when disable pull refresh.
	private RelativeLayout mHeaderViewContent;
	private TextView mHeaderTimeView;
	private int mHeaderViewHeight; // header view's height
	private boolean mEnablePullRefresh = true;
	private boolean mPullRefreshing = false; // is refreashing.

	// -- footer view
	private XListViewFooter mFooterView;
//	private XListViewFooterJzg mFooterView;
	private boolean mEnablePullLoad;
	private boolean mPullLoading;
	private boolean mIsFooterReady = false;

	// total list items, used to detect is at the bottom of listview.
	private int mTotalItemCount;

	// for mScroller, scroll back from header or footer.
	private int mScrollBack;
	private final static int SCROLLBACK_HEADER = 0;
	private final static int SCROLLBACK_FOOTER = 1;

	private final static int SCROLL_DURATION = 400; // scroll back duration
	private final static int PULL_LOAD_MORE_DELTA = 50; // when pull up >= 50px
														// at bottom, trigger
														// load more.
	private final static float OFFSET_RADIO = 1.8f; // support iOS like pull
													// feature.
	
	

	public SwipeMenuListView(Context context) {
		super(context);
		init(context);
	}

	public SwipeMenuListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public SwipeMenuListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		MAX_X = dp2px(MAX_X);
		MAX_Y = dp2px(MAX_Y);
		mTouchState = TOUCH_STATE_NONE;
		
		mScroller = new Scroller(context, new DecelerateInterpolator());
		// XListView need the scroll event, and it will dispatch the event to
				// user's listener (as a proxy).
				super.setOnScrollListener(this);

				// init header view
				mHeaderView = new XListViewHeader(context);
				mHeaderViewContent = (RelativeLayout) mHeaderView
						.findViewById(R.id.xlistview_header_content);
				mHeaderTimeView = (TextView) mHeaderView
						.findViewById(R.id.xlistview_header_time);
				addHeaderView(mHeaderView);

				// init footer view
				mFooterView = new XListViewFooter(context);
			//	mFooterView = new XListViewFooterJzg(context);
				// init header height
				mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(
						new OnGlobalLayoutListener()
						{
							@Override
							public void onGlobalLayout()
							{
								mHeaderViewHeight = mHeaderViewContent.getHeight();
								getViewTreeObserver()
										.removeGlobalOnLayoutListener(this);
							}
						});
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		// make sure XListViewFooter is the last footer view, and only add once.
				if (mIsFooterReady == false)
				{
					mIsFooterReady = true;
					addFooterView(mFooterView);
				}
				
		super.setAdapter(new SwipeMenuAdapter(getContext(), adapter) {
			@Override
			public void createMenu(SwipeMenu menu) {
				if (mMenuCreator != null) {
					mMenuCreator.create(menu);
				}
			}

			@Override
			public void onItemClick(SwipeMenuView view, SwipeMenu menu,
					int index) {
				if (mOnMenuItemClickListener != null) {
					mOnMenuItemClickListener.onMenuItemClick(
							view.getPosition(), menu, index);
				}
				if (mTouchView != null) {
					mTouchView.smoothCloseMenu();
				}
			}
		});
	}
	
	/**
	 * 设置HeaderView 图片， 设置图片是否旋转
	 * 隐藏进度圈
	 * @param imgResource
	 * @param hasAnimation
	 */
	public void setRefreshHeaderArrow(int imgResource,boolean hasAnimation){
		ImageView arrowView = (ImageView) mHeaderView.findViewById(R.id.xlistview_header_arrow);
		arrowView.setImageResource(imgResource);
		mHeaderView.mHasAnimation = hasAnimation;
		
	}
	/**
	 * 设置精真估小黄车加载动画
	 * @param isShowHeaderAnim	是否显示刷新动画，默认不显示
	 * @param isShowFooterAnim	是否显示加载更多动画，默认不显示
	 */
	public void isShowCarRefreshAndLoadMoreAnim(boolean isShowHeaderAnim,boolean isShowFooterAnim){
		mHeaderView.setIsShowRefreshAnim(isShowHeaderAnim);
		mFooterView.setIsShowLoadMoreAnim(isShowFooterAnim);
		mHeaderView.mHasAnimation = false;
	}
	public void setCloseInterpolator(Interpolator interpolator){
		mCloseInterpolator = interpolator;
	}
	
	public void setOpenInterpolator(Interpolator interpolator){
		mOpenInterpolator = interpolator;
	}
	
	public Interpolator getOpenInterpolator(){
		return mOpenInterpolator;
	}
	
	public Interpolator getCloseInterpolator(){
		return mCloseInterpolator;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (mLastY == -1)
		{
			mLastY = ev.getRawY();
		}
		
		int action = MotionEventCompat.getActionMasked(ev); // 	action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mDownX = ev.getX();
			mDownY = ev.getY();
			mTouchState = TOUCH_STATE_NONE;

			if (mTouchView != null && mTouchView.isOpen()) {
				mTouchView.smoothCloseMenu();
				return false;
			}
			mTouchPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
			View view = getChildAt(mTouchPosition - getFirstVisiblePosition());
			if (view instanceof SwipeMenuLayout) {
				mTouchView = (SwipeMenuLayout) view;
			}
			if (mTouchView != null) {
				mTouchView.onSwipe(ev);
			}
			
			mLastY = ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			float dy = Math.abs((ev.getY() - mDownY));
			float dx = Math.abs((ev.getX() - mDownX));
			if (mTouchState == TOUCH_STATE_X) {
				// 婊戝姩
				if (mTouchView != null) {
					mTouchView.onSwipe(ev);
				}
				getSelector().setState(new int[] { 0 });
				ev.setAction(MotionEvent.ACTION_CANCEL);
				super.onTouchEvent(ev);
				return true;
			} else {
				// Log.i("byz", "state = " + mTouchState + ", dx = " + dx
				// + ", max = " + MAX_X);
				if (mTouchState != TOUCH_STATE_X && Math.abs(dy) > MAX_Y) {
					mTouchState = TOUCH_STATE_Y;
				}
				if (mTouchState != TOUCH_STATE_Y && dx > MAX_X) {
					mTouchState = TOUCH_STATE_X;
				}
			}
			
			final float deltaY = ev.getRawY() - mLastY;
			mLastY = ev.getRawY();
			if (getFirstVisiblePosition() == 0
					&& (mHeaderView.getVisiableHeight() > 0 || deltaY > 0))
			{
				// the first item is showing, header has shown or pull down.
				updateHeaderHeight(deltaY / OFFSET_RADIO);
				invokeOnScrolling();
			} else if (getLastVisiblePosition() == mTotalItemCount - 1
					&& (mFooterView.getBottomMargin() > 0 || deltaY < 0))
			{
				// last item, already pulled up or want to pull up.
				updateFooterHeight(-deltaY / OFFSET_RADIO);
			}
			
			break;
		case MotionEvent.ACTION_UP:
			if (mTouchState == TOUCH_STATE_X) {
				if (mTouchView != null) {
					mTouchView.onSwipe(ev);
				}
				ev.setAction(MotionEvent.ACTION_CANCEL);
				super.onTouchEvent(ev);
				return true;
			}
			mLastY = -1; // reset
			if (getFirstVisiblePosition() == 0)
			{
				// invoke refresh
				if (mEnablePullRefresh
						&& mHeaderView.getVisiableHeight() > mHeaderViewHeight)
				{
					mPullRefreshing = true;
					mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
					if (mListViewListener != null)
					{
						mListViewListener.onRefresh();
					}
				}
				resetHeaderHeight();
			} else if (getLastVisiblePosition() == mTotalItemCount - 1)
			{
				// invoke load more.
				if (mEnablePullLoad
						&& mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA
						&& !mPullLoading)
				{
					startLoadMore();
				}
				resetFooterHeight();
			}
			
			
			break;
			
		default:
			
			break;
		}
		return super.onTouchEvent(ev);
	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getContext().getResources().getDisplayMetrics());
	}

	public void setMenuCreator(SwipeMenuCreator menuCreator) {
		this.mMenuCreator = menuCreator;
	}

	public void setOnMenuItemClickListener(
			OnMenuItemClickListener onMenuItemClickListener) {
		this.mOnMenuItemClickListener = onMenuItemClickListener;
	}

	public static interface OnMenuItemClickListener {
		void onMenuItemClick(int position, SwipeMenu menu, int index);
	}

	/**
	 * enable or disable pull down refresh feature.
	 * 
	 * @param enable
	 */
	public void setPullRefreshEnable(boolean enable)
	{
		mEnablePullRefresh = enable;
		if (!mEnablePullRefresh)
		{ // disable, hide the content
			mHeaderViewContent.setVisibility(View.INVISIBLE);
		} else
		{
			mHeaderViewContent.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * enable or disable pull up load more feature.
	 * 
	 * @param enable
	 */
	public void setPullLoadEnable(boolean enable)
	{
		mEnablePullLoad = enable;
		if (!mEnablePullLoad)
		{
			mFooterView.hide();
			mFooterView.setOnClickListener(null);
		} else
		{
			mPullLoading = false;
			mFooterView.show();
			mFooterView.setState(XListViewFooter.STATE_NORMAL);
			// both "pull up" and "click" will invoke load more.
			mFooterView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					startLoadMore();
				}
			});
		}
	}

	/**
	 * stop refresh, reset header view.
	 */
	public void stopRefresh()
	{
		if (mPullRefreshing == true)
		{
			mPullRefreshing = false;
			resetHeaderHeight();
		}
	}

	/**
	 * stop load more, reset footer view.
	 */
	public void stopLoadMore()
	{
		if (mPullLoading == true)
		{
			mPullLoading = false;
			mFooterView.setState(XListViewFooter.STATE_NORMAL);
		}
	}

	/**
	 * set last refresh time
	 * 
	 * @param time
	 */
	public void setRefreshTime(String time)
	{
		mHeaderTimeView.setText(time);
	}

	private void invokeOnScrolling()
	{
		if (mScrollListener instanceof OnXScrollListener)
		{
			OnXScrollListener l = (OnXScrollListener) mScrollListener;
			l.onXScrolling(this);
		}
	}

	private void updateHeaderHeight(float delta)
	{
		mHeaderView.setVisiableHeight((int) delta
				+ mHeaderView.getVisiableHeight());
		if (mEnablePullRefresh && !mPullRefreshing)
		{ // 未处于刷新状态，更新箭头
			if (mHeaderView.getVisiableHeight() > mHeaderViewHeight)
			{
				mHeaderView.setState(XListViewHeader.STATE_READY);
			} else
			{
				mHeaderView.setState(XListViewHeader.STATE_NORMAL);
			}
		}
		setSelection(0); // scroll to top each time
	}

	/**
	 * reset header view's height.
	 */
	private void resetHeaderHeight()
	{
		int height = mHeaderView.getVisiableHeight();
		if (height == 0) // not visible.
			return;
		// refreshing and header isn't shown fully. do nothing.
		if (mPullRefreshing && height <= mHeaderViewHeight)
		{
			return;
		}
		int finalHeight = 0; // default: scroll back to dismiss header.
		// is refreshing, just scroll back to show all the header.
		if (mPullRefreshing && height > mHeaderViewHeight)
		{
			finalHeight = mHeaderViewHeight;
		}
		mScrollBack = SCROLLBACK_HEADER;
		mScroller.startScroll(0, height, 0, finalHeight - height,
				SCROLL_DURATION);
		// trigger computeScroll
		invalidate();
	}

	private void updateFooterHeight(float delta)
	{
		int height = mFooterView.getBottomMargin() + (int) delta;
		if (mEnablePullLoad && !mPullLoading)
		{
			if (height > PULL_LOAD_MORE_DELTA)
			{ // height enough to invoke load
				// more.
				mFooterView.setState(XListViewFooter.STATE_READY);
			} else
			{
				mFooterView.setState(XListViewFooter.STATE_NORMAL);
			}
		}
		mFooterView.setBottomMargin(height);

		// setSelection(mTotalItemCount - 1); // scroll to bottom
	}

	private void resetFooterHeight()
	{
		int bottomMargin = mFooterView.getBottomMargin();
		if (bottomMargin > 0)
		{
			mScrollBack = SCROLLBACK_FOOTER;
			mScroller.startScroll(0, bottomMargin, 0, -bottomMargin,
					SCROLL_DURATION);
			invalidate();
		}
	}

	private void startLoadMore()
	{
		mPullLoading = true;
		mFooterView.setState(XListViewFooter.STATE_LOADING);
		if (mListViewListener != null)
		{
			mListViewListener.onLoadMore();
		}
	}

	
	@Override
	public void computeScroll()
	{
		if (mScroller.computeScrollOffset())
		{
			if (mScrollBack == SCROLLBACK_HEADER)
			{
				mHeaderView.setVisiableHeight(mScroller.getCurrY());
			} else
			{
				mFooterView.setBottomMargin(mScroller.getCurrY());
			}
			postInvalidate();
			invokeOnScrolling();
		}
		super.computeScroll();
	}

	@Override
	public void setOnScrollListener(OnScrollListener l)
	{
		mScrollListener = l;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState)
	{
		if (mScrollListener != null)
		{
			mScrollListener.onScrollStateChanged(view, scrollState);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount)
	{
		// send to user's listener
		mTotalItemCount = totalItemCount;
		if (mScrollListener != null)
		{
			mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
					totalItemCount);
		}
	}

	public void setXListViewListener(IXListViewListener l)
	{
		mListViewListener = l;
	}

	/**
	 * you can listen ListView.OnScrollListener or this one. it will invoke
	 * onXScrolling when header/footer scroll back.
	 */
	public interface OnXScrollListener extends OnScrollListener
	{
		public void onXScrolling(View view);
	}

	/**
	 * implements this interface to get refresh/load more event.
	 */
	public interface IXListViewListener
	{
		public void onRefresh();

		public void onLoadMore();
	}

	public XListViewFooter getmFooterView()
	{
		return mFooterView;
	}

	public void setmFooterView(XListViewFooter mFooterView)
	{
		this.mFooterView = mFooterView;
	}
}
