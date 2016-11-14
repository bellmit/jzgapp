package com.jzg.jzgoto.phone.view.buy;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.car.CarModelBean;
import com.jzg.jzgoto.phone.valuationchoosecarstyle.JzgCarChooseModelCategoryAdapter1;
import com.jzg.pricechange.phone.JzgCarChooseChineseUtil;
import com.jzg.pricechange.phone.JzgCarChooseConstant;
import com.jzg.pricechange.phone.JzgCarChooseCustomArrayAdapter.OnImgClickListener;
import com.jzg.pricechange.phone.JzgCarChooseMake;
import com.jzg.pricechange.phone.JzgCarChooseModel;
import com.jzg.pricechange.phone.JzgCarChooseModelCategory;
import com.jzg.pricechange.phone.JzgCarChooseMyLetterListView;
import com.jzg.pricechange.phone.JzgCarChooseMyLetterListView.OnTouchingLetterChangedListener;
import com.jzg.pricechange.phone.JzgCarChooseStyle;
import com.jzg.pricechange.phone.JzgCarChooseStyleCategory;
import com.jzg.pricechange.phone.JzgCarChooseStyleCategoryAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BuyCarSelfCarStyleListRelative extends RelativeLayout implements
		OnItemClickListener, OnDrawerOpenListener, OnDrawerCloseListener,
		OnScrollListener, OnImgClickListener {

	public BuyCarSelfCarStyleListRelative(Context context, AttributeSet attrs,
										  int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public BuyCarSelfCarStyleListRelative(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private PopupWindow mPopWindow;
	public BuyCarSelfCarStyleListRelative(Context context, PopupWindow popWindow) {
		super(context);
		this.mPopWindow = popWindow;
		initView();
	}

	private void initView() {
		View.inflate(getContext(), R.layout.view_buycar_self_carstyle_layout, this);

		mOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.jzgcarchoose_jingzhengu)
				.showImageForEmptyUri(R.drawable.jzgcarchoose_jingzhengu)
				.showImageOnFail(R.drawable.jzgcarchoose_jingzhengu).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();
		init();
	}

	protected ImageLoader imageLoader = ImageLoader.getInstance();
	/**
	 * →_→ 字母
	 */
	private JzgCarChooseMyLetterListView mIndexListView = null;
	/**
	 * 汽车品牌显示
	 */
	private ListView mIndexCarListView = null;

	// 存放存在的汉语拼音首字母
	private String[] sections;

	// 存放存在的汉语拼音首字母和与之对应的列表位置
	private HashMap<String, Integer> index;

	/**
	 * 汽车系列drawer,车型
	 */
	private SlidingDrawer mCarTypeDrawer;
	private ImageView mCarTypeHandle;
	private ListView mCarTypeContent;

	/**
	 * 汽车类型drawer
	 */
	private SlidingDrawer mCarYearstyleDrawer;
	private ImageView mCarYearstyleHandle;
	private ListView mCarYearstyleContent;


	/**
	 * 汽车品牌排序集合
	 */
	private ArrayList<Map<String, Object>> items;

	/**
	 * 当前选择的汽车品牌
	 */
	private String mCurMake;

	/**
	 * 当前选择的品牌id
	 */
	private int mCurMakeid;

	/**
	 * 当前选择的汽车车系
	 */
	private String mCurModel;

	/**
	 * 当前选择的车系id
	 */
	private int mCurModelid;

	/**
	 * 解析后车系json数据封装
	 */
	private List<JzgCarChooseModelCategory> mModelCategorys;

	/**
	 * 品牌列表所有数据
	 */
	private ArrayList<JzgCarChooseMake> makes;

	/**
	 * 判断品牌列表的数据是否为空
	 *
	 * @return
	 */
	public boolean checkHasMakesList() {
		if (makes == null || makes.size() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 车系列表所有数据包括标题
	 */
	private List<JzgCarChooseModel> mModels;

	public void setModelsList(List<JzgCarChooseModel> list) {
		if (list == null) {
			return;
		}
		if (mModels != null) {
			mModels = null;
		}
		mModels = list;
	}

	/**
	 * 所有车系标题
	 */
	private List<String> mModelsGroupkey;

	/**
	 * 车型列表所有数据包括标题
	 */
	private List<JzgCarChooseStyle> mStyles;

	/**
	 * 所有车型标题
	 */
	private List<String> mStylessGroupkey;

	/**
	 * 车型json解析数据封装
	 */
	private List<JzgCarChooseStyleCategory> carStyles;

	/**
	 * 品牌列表适配器
	 */
	private ListAdapter mIndexCarListAdapter;

	/**
	 * 车系列表适配器
	 */
	private JzgCarChooseModelCategoryAdapter1 mModelCategoryAdapter;

	/**
	 * 车型列表适配器
	 */
	private JzgCarChooseStyleCategoryAdapter mStyleCategoryAdapter;

	/**
	 * 品牌列表的上一次点击位置
	 */
	private int mMakeListOldPosition = -1;

	/**
	 * 车系列表的上一次点击位置
	 */
	private int mModelListOldPosition = -1;

	public void clearClickPosition(){
		mMakeListOldPosition = -1;
		mModelListOldPosition = -1;
		colseDrawer(mCarTypeDrawer);
		if(makes!=null)makes.clear();
	}
	/**
	 * 车型列表的上一次点击位置
	 */
	private int mStyleListOldPosition = -1;

	/**
	 * 图片加载配置选项
	 */
	private DisplayImageOptions mOptions;

	/**
	 * 加载中提示框
	 */
	private Dialog mDialog;

	public static final int QUERYCAR_MSG = 1;// 车辆信息

	private String carFilterModleFlag = "";

	/**
	 * 判断是否为两列(默认可以选择两列)
	 * false--只能选择品牌
	 */
	private boolean canChooseModel = true;
	public void setCanChooseModel(boolean model){
		this.canChooseModel = model;
	}
	/**
	 * 筛选过程中选择的车型项
	 */
	// private List<Model> models;

	// private LinearLayout chooseLayout;

	private boolean isFirst;

	/**
	 * 显示品牌列表数据
	 */
	public void showMakeList(ArrayList<JzgCarChooseMake> makes1) {
		if (makes != null) {
			makes.clear();
		}
		makes = new ArrayList<JzgCarChooseMake>();
		// 品牌实体
		JzgCarChooseMake ma = new JzgCarChooseMake();
		makes.add(ma);

		makes.addAll(makes1);
		// 设置颜色
		setMakeColor();
		String contactSort = null;
		items = new ArrayList<Map<String, Object>>();

		Map<String, Object> allM = new HashMap<String, Object>();
		allM.put("name", "#不限");
		allM.put("fontColor", Color.BLACK);
		allM.put("logo", "");
		allM.put("Sort", "");
		allM.put("itemColor", Color.WHITE);
		items.add(allM);

		if (makes1 != null) {
			Map<String, Object> map = null;
			for (int i = 0; i < makes1.size(); i++) {
				map = new HashMap<String, Object>();
				map.put("name", makes1.get(i).getMakeName());
				map.put("fontColor", makes1.get(i).getFontColor());
				map.put("logo", makes1.get(i).getMakeLogo());
				contactSort = JzgCarChooseChineseUtil
						.getFullSpell(map.get("name").toString()).toUpperCase()
						.substring(0, 1);
				map.put("Sort", contactSort);
				map.put("itemColor", makes1.get(i).getItemColor());
				items.add(map);
			}
			// 按中文进行排序的类
			Comparator comp = new Mycomparator();
			Collections.sort(items, comp);

			mIndexCarListAdapter = new ListAdapter(getContext(), items);
			mIndexCarListView.setAdapter(mIndexCarListAdapter);

			if (mMakeListOldPosition != -1) {
				mIndexCarListView.setSelection(mMakeListOldPosition);
			}
		}
	}

	private void setMakeColor() {
		// 设置所有颜色为原色
		for (JzgCarChooseMake make : makes) {
			make.setFontColor(Color.BLACK);
			make.setItemColor(Color.WHITE);
		}

		// 设置点击item的点击颜色
		if (mMakeListOldPosition != -1) {
			makes.get(mMakeListOldPosition).setItemColor(
					getResources().getColor(R.color.grey2));
		}

	}



	public void showModelList(List<JzgCarChooseModelCategory> list) {
		openDrawer(mCarTypeDrawer);
		if(makes.get(mMakeListOldPosition) != null){
			JzgCarChooseMake make = makes.get(mMakeListOldPosition);
			ImageLoader.getInstance().displayImage(make.getMakeLogo(),mChooseModelIcon);
			mChooseModelName.setText(make.getMakeName());
		}
		mModels = new ArrayList<JzgCarChooseModel>();
		mModelsGroupkey = new ArrayList<String>();
		mModelsGroupkey.add("不限");
		JzgCarChooseModel model1 = new JzgCarChooseModel();
		model1.setName("#不限");
		model1.setManufacturerName(JzgCarChooseConstant.IS_TITLE);
		model1.setFontColor(Color.BLACK);
		mModels.add(model1);
		for (JzgCarChooseModelCategory category : mModelCategorys) {
			JzgCarChooseModel model = new JzgCarChooseModel();
			String groupName = category.getmCategoryName();
			mModelsGroupkey.add(groupName);
			model.setName(groupName);
			model.setManufacturerName(JzgCarChooseConstant.IS_TITLE);
			model.setFontColor(getResources().getColor(R.color.categroy_title));
			mModels.add(model);
			mModels.addAll(category.getmCategoryItem());
		}

		setModelColor();

		mModelCategoryAdapter = new JzgCarChooseModelCategoryAdapter1(
				getContext(), mModels, mModelsGroupkey);
		mCarTypeContent.setAdapter(mModelCategoryAdapter);

	}


	/**
	 * 显示车系列表
	 *
	 */
	public void showModelListNew(List<CarModelBean> modelLists) {
		openDrawer(mCarTypeDrawer);
		if(makes.get(mMakeListOldPosition) != null){
			JzgCarChooseMake make = makes.get(mMakeListOldPosition);
			ImageLoader.getInstance().displayImage(make.getMakeLogo(),mChooseModelIcon);
			mChooseModelName.setText(make.getMakeName());
		}
		mModels = new ArrayList<JzgCarChooseModel>();
		mModelsGroupkey = new ArrayList<String>();
		mModelsGroupkey.add("不限");
		JzgCarChooseModel model1 = new JzgCarChooseModel();
		model1.setName("#不限");
		model1.setManufacturerName(JzgCarChooseConstant.IS_TITLE);
		model1.setFontColor(Color.BLACK);
		mModels.add(model1);

		Set<String> set = new LinkedHashSet<String>();
//		Collections.reverse(modelLists);
		for (CarModelBean carModelBean : modelLists) {
			set.add(carModelBean.getManufacturerName());
		}
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String title = it.next();
			System.out.println(title);
			ArrayList<JzgCarChooseModel>  list = new ArrayList<JzgCarChooseModel>();
			for (CarModelBean carModelBean : modelLists) {
				if(title.equals(carModelBean.getManufacturerName())){
					JzgCarChooseModel JzgCarChooseModel = new JzgCarChooseModel();
					JzgCarChooseModel.setId(Integer.parseInt(carModelBean.getId()));
					JzgCarChooseModel.setName(carModelBean.getName());
					JzgCarChooseModel.setManufacturerName(carModelBean.getManufacturerName());
					JzgCarChooseModel.setModelimgpath(carModelBean.getModelimgpath());
					list.add(JzgCarChooseModel);
				}
			}
			JzgCarChooseModel model = new JzgCarChooseModel();
			mModelsGroupkey.add(title);
			model.setName(title);
			model.setManufacturerName(JzgCarChooseConstant.IS_TITLE);
			model.setFontColor(getResources().getColor(R.color.categroy_title));
			mModels.add(model);
			mModels.addAll(list);

		}


//		for (JzgCarChooseModelCategory category : mModelCategorys) {
//			JzgCarChooseModel model = new JzgCarChooseModel();
//
//
//
//			String groupName = category.getmCategoryName();
//			mModelsGroupkey.add(groupName);
//			model.setName(groupName);
//			model.setManufacturerName(JzgCarChooseConstant.IS_TITLE);
//			model.setFontColor(getResources().getColor(R.color.categroy_title));
//			mModels.add(model);
//			mModels.addAll(category.getmCategoryItem());
//		}

		setModelColor();

		mModelCategoryAdapter = new JzgCarChooseModelCategoryAdapter1(
				getContext(), mModels, mModelsGroupkey);
		mCarTypeContent.setAdapter(mModelCategoryAdapter);

	}

	private void setModelColor() {
		// 设置所有颜色为原色
		for (JzgCarChooseModel model : mModels){
			if (JzgCarChooseConstant.IS_TITLE.equals(model.getManufacturerName())) {
				model.setFontColor(getResources().getColor(
						R.color.categroy_title));
			} else {
				model.setFontColor(Color.BLACK);
				model.setItemColor(Color.WHITE);
			}
		}
		mModels.get(0).setFontColor(Color.BLACK);
		mModels.get(0).setItemColor(Color.WHITE);
		// 设置点击item的点击颜色
		if (mModelListOldPosition != -1)
			mModels.get(mModelListOldPosition).setItemColor(
					getResources().getColor(R.color.grey2));
	}

//	/**
//	 * 显示车型列表
//	 *
//	 * @param carStyles
//	 */
//	public void showStyleList(List<JzgCarChooseStyleCategory> carStyles) {
//		openDrawer(mCarYearstyleDrawer);
//		mStyles = new ArrayList<JzgCarChooseStyle>();
//		mStylessGroupkey = new ArrayList<String>();
//		mStylessGroupkey.add("不限");
//		JzgCarChooseStyle style1 = new JzgCarChooseStyle();
//		style1.setName("#不限");
//		style1.setManufacturerName(JzgCarChooseConstant.IS_TITLE);
//		style1.setFontColor(getResources().getColor(R.color.categroy_title));
//		style1.setItemColor(Color.WHITE);
//		mStyles.add(style1);
//		for (JzgCarChooseStyleCategory category : carStyles) {
//			JzgCarChooseStyle style = new JzgCarChooseStyle();
//			String groupName = category.getYearTitle();
//			mStylessGroupkey.add(groupName);
//			style.setName(groupName);
//			style.setManufacturerName(JzgCarChooseConstant.IS_TITLE);
//			style.setFontColor(getResources().getColor(R.color.categroy_title));
//			style.setItemColor(Color.WHITE);
//			// 添加标题到列表
//			mStyles.add(style);
//			// 添加所有选项到列表
//			mStyles.addAll(category.getCategoryItem());
//		}
//
//		setStyleColor();
//
//		mStyleCategoryAdapter = new JzgCarChooseStyleCategoryAdapter(
//				getContext(), mStyles, mStylessGroupkey);
//		mCarYearstyleContent.setAdapter(mStyleCategoryAdapter);
//
//	}

//	private void setStyleColor() {
//		// 设置所有颜色为原色
//		for (JzgCarChooseStyle style : mStyles)
//			if (mStylessGroupkey.contains(style.getName())) {
//				style.setFontColor(getResources().getColor(
//						R.color.categroy_title));
//			} else {
//				style.setFontColor(Color.BLACK);
//				style.setItemColor(Color.WHITE);
//			}
//		// 设置点击item的点击颜色
//		if (mStyleListOldPosition != -1)
//			mStyles.get(mStyleListOldPosition).setItemColor(
//					getResources().getColor(R.color.grey2));
//	}

	protected void openDrawer(SlidingDrawer mDrawer) {
		if (!mDrawer.isOpened()) {
			mDrawer.animateOpen();
		}
	}

	protected void colseDrawer(SlidingDrawer mDrawer) {
		if(mDrawer!=null)
			if (mDrawer.isOpened()) {
				mDrawer.close();
			}
	}

	// 按中文拼音排序
	public class Mycomparator implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			Map<String, Object> c1 = (Map<String, Object>) o1;
			Map<String, Object> c2 = (Map<String, Object>) o2;
			Comparator cmp = Collator.getInstance(java.util.Locale.ENGLISH);
			return cmp.compare(c1.get("Sort"), c2.get("Sort"));
		}
	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
									  Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

	private ImageView mChooseModelIcon;
	private TextView mChooseModelName;
	public void init() {
		isFirst = true;
		findViewById(R.id.view_bottom_out_listview).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(mPopWindow!=null){
					if(mPopWindow.isShowing()){
						mPopWindow.dismiss();
					}
				}
			}
		});
		// 字母列表
		mIndexListView = (JzgCarChooseMyLetterListView) findViewById(R.id.index_car_index_list);
		mIndexListView
				.setOnTouchingLetterChangedListener(new LetterListViewListener());

		// 初始化汽车品牌
		mIndexCarListView = (ListView) findViewById(R.id.index_car_list);
		mIndexCarListView.setOnItemClickListener(this);

		// 初始化汽车类型
		mCarTypeDrawer = (SlidingDrawer) findViewById(R.id.index_car_type_drawer);
		mCarTypeHandle = (ImageView) findViewById(R.id.index_car_type_handle);
		mCarTypeContent = (ListView) findViewById(R.id.index_car_type_list_content);
		mChooseModelIcon = (ImageView) findViewById(R.id.make_logo_img);
		mChooseModelName = (TextView) findViewById(R.id.make_name_text);
		mCarTypeContent.setOnItemClickListener(this);

		// 设置SlidingDrawer打开或者关闭时的监听器，设置失去
		mCarTypeDrawer.setOnDrawerOpenListener(this);
		mCarTypeDrawer.setOnDrawerCloseListener(this);

		// 设置mCarTypeDrawer滑动时监听，如果mCarYearstyleDrawer是打开状态则先关闭
		mCarTypeDrawer.setOnDrawerScrollListener(new OnDrawerScrollListener() {
			@Override
			public void onScrollStarted() {
				colseDrawer(mCarYearstyleDrawer);
			}

			@Override
			public void onScrollEnded() {

			}
		});

		// 初始化汽车 车型 《《--- 车系
		mCarYearstyleDrawer = (SlidingDrawer) findViewById(R.id.index_car_yearstyle_drawer);
		mCarYearstyleHandle = (ImageView) findViewById(R.id.index_car_yearstyle_handle);
		mCarYearstyleContent = (ListView) findViewById(R.id.index_car_yearstyle_content);
		mCarYearstyleContent.setOnItemClickListener(this);

		// 设置SlidingDrawer打开或者关闭时的监听器
		mCarYearstyleDrawer.setOnDrawerOpenListener(this);
		mCarYearstyleDrawer.setOnDrawerCloseListener(this);

		mIndexCarListView.setOnScrollListener(this);
		mCarTypeContent.setOnScrollListener(this);
	}
	public void toHideBottomView(){
		//隐藏底部透明View
		findViewById(R.id.view_bottom_out_listview).setVisibility(View.GONE);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
		int viewid = parent.getId();
		switch (viewid) {
			// TODO 判断是否要获取车系列表 ---- 品牌列表点击
			case R.id.index_car_list:
				if(canChooseModel){
					//选两列
					makeItemClick(view, position);

					if (mMakeListOldPosition == 0) {
						JzgCarChooseStyle style = new JzgCarChooseStyle();
						style.setBrandId(0);
						style.setBrandName("不限");
						style.setModeId(0);
						style.setModeName("");
						style.setId(0);
						style.setName("");

						colseDrawer(mCarYearstyleDrawer);
						colseDrawer(mCarTypeDrawer);

						mCallbackForCarList.toCallBackCarStyle(style);
						break;
					}

					if (mModels == null || mModels.size() == 0) {
						// TODO 请求列表
						if (mCallbackForCarList != null) {
							mCallbackForCarList.toRequestCarModel(String
									.valueOf(mCurMakeid));
						}
						break;
					}
				}else{
					//选一列
					JzgCarChooseStyle style = new JzgCarChooseStyle();
					style.setModeId(0);
					style.setModeName("");
					style.setId(0);
					style.setName("");
					if (position == 0) {
						style.setBrandId(0);
						style.setBrandName("不限");
						style.setFullName("不限");
					}else{
						style.setBrandId(makes.get(position).getMakeId());
						style.setBrandName(makes.get(position).getMakeName());
						style.setFullName(makes.get(position).getMakeName());
					}
					colseDrawer(mCarYearstyleDrawer);
					colseDrawer(mCarTypeDrawer);
					mCallbackForCarList.toCallBackCarStyle(style);
				}
				break;
			// TODO 判断是否要获取车型列表 ---- 车系列表点击
			case R.id.index_car_type_list_content:
				modelItemClick(view, position);
				if (position == 0) {
					JzgCarChooseStyle style = new JzgCarChooseStyle();
					style.setBrandId(mCurMakeid);
					style.setBrandName(mCurMake);
					style.setModeId(0);
					style.setModeName("");
					style.setId(0);
					style.setName("");
					colseDrawer(mCarYearstyleDrawer);
					// colseDrawer(mCarTypeDrawer);

					mCallbackForCarList.toCallBackCarStyle(style);
					break;
				}
				//两列选车系
				if(mHasCheckModel!=null){
					JzgCarChooseStyle style = new JzgCarChooseStyle();
					style.setBrandId(mCurMakeid);
					style.setBrandName(mCurMake);
					style.setModeId(mHasCheckModel.getId());
					style.setModeName(mHasCheckModel.getName());
					style.setId(-1);
					style.setName("");

					colseDrawer(mCarYearstyleDrawer);
					// colseDrawer(mCarTypeDrawer);

					mCallbackForCarList.toCallBackCarStyle(style);
				}
//			if (mStyles == null || mStyles.size() == 0) {
//				// TODO 请求列表
//				if (mCallbackForCarList != null) {
//					mCallbackForCarList.toRequestCarStyle(String
//							.valueOf(mCurModelid));
//				}
//				break;
//			}
				break;
			// TODO 车型列表点击
			case R.id.index_car_yearstyle_content:
				styleItemClick(view, position);

				break;
			default:
				break;
		}
	}

	/**
	 * 车型点击
	 *
	 * @param position
	 */
	private void styleItemClick(View view, int position) {
		// 当车型列表被点击时，存储当前点击的位置
		mStyleListOldPosition = position;
		JzgCarChooseStyle style = mStyles.get(position);
		if (position == 0) {
			style.setBrandId(mCurMakeid);
			style.setBrandName(mCurMake);
			style.setModeId(mCurModelid);
			style.setModeName(mCurModel);
			style.setId(-1);
			style.setName("#不限");

			// colseDrawer(mCarYearstyleDrawer);
			// colseDrawer(mCarTypeDrawer);
			if (mCallbackForCarList != null) {
				mCallbackForCarList.toCallBackCarStyle(style);
			}
		} else {
			style.setBrandId(mCurMakeid);
			style.setBrandName(mCurMake);
			style.setModeId(mCurModelid);
			style.setModeName(mCurModel);
			style.setModelimgpath(mHasCheckModel.getModelimgpath());

			if (mCallbackForCarList != null) {
				mCallbackForCarList.toCallBackCarStyle(style);
			}
		}

	}

	private JzgCarChooseModel mHasCheckModel;

	/**
	 * 车系 点击
	 *
	 * @param view
	 * @param position
	 */
	private void modelItemClick(View view, int position) {
		// 当车系列表被点击时，存储当前点击的位置
		if (mModelListOldPosition != position) {
			mStyles = null;
			mStyleListOldPosition = -1;
		}
		mModelListOldPosition = position;

		modifyModelFontColor(view, R.id.addexam_list_item_text,
				R.color.list_click, position);
		mCurModel = mModels.get(position).getName();
		mHasCheckModel = mModels.get(position);
		mCurModelid = mModels.get(position).getId();

		if (mStylessGroupkey != null) {
			mStylessGroupkey.clear();
			mStyleCategoryAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * 品牌点击
	 *
	 * @param view
	 * @param position
	 */
	private void makeItemClick(View view, int position) {
		// 当品牌列表被点击的时候存储当前点击的位置
		if (mMakeListOldPosition != position) {
			mModels = null;
			mModelListOldPosition = -1;
		}
		mMakeListOldPosition = position;

		if (position == 0) {
			mCurMake = "";
			mCurMakeid = -1;
			// TODO 颜色变化稍后添加，目前先把数据的逻辑理清
			modifyMakeFontColor(view, R.id.car_name, R.color.list_click,
					position);
			colseDrawer(mCarYearstyleDrawer);
			return;
		}

		final JzgCarChooseMake make = makes.get(position);
		final String makeid = String.valueOf(make.getMakeId());
		// 设置当前选择的品牌、id
		mCurMake = make.getMakeName();
		mCurMakeid = make.getMakeId();
		// TODO 颜色变化稍后添加，目前先把数据的逻辑理清
		modifyMakeFontColor(view, R.id.car_name, R.color.list_click, position);
		colseDrawer(mCarYearstyleDrawer);
	}

	/**
	 * 修改品牌列表字体点击颜色 modifyMakeFontColor: <br/>
	 *
	 * @author wang
	 * @param view
	 * @param position
	 * @since JDK 1.6
	 */
	private void modifyMakeFontColor(View view, int resid, int listClickColor,
									 int position) {

		// 设置所有颜色为原色
		for (int i = 0; i < items.size(); i++) {
			items.get(i).put("fontColor", Color.BLACK);
			items.get(i).put("itemColor", Color.WHITE);
		}

		TextView textView = (TextView) view.findViewById(resid);
		textView.setTextColor(getResources().getColor(listClickColor));

		// items.get(position).put("fontColor",
		// getResources().getColor(listClickColor));
		// 原有版本改变为黄色字体的代码，用下面代码替换，不改变字体颜色
		items.get(position).put("fontColor", Color.BLACK);
		items.get(position).put("itemColor",
				getResources().getColor(R.color.grey2));

		mIndexCarListAdapter.notifyDataSetChanged();
	}

	/**
	 * 修改车系列表字体点击颜色 modifyFontColor: <br/>
	 */
	private void modifyModelFontColor(View view, int resid, int listClickColor,
									  int position) {
		try {
			for (JzgCarChooseModel model : mModels) {
				if (TextUtils.isEmpty(model.getManufacturerName())) {
					model.setFontColor(Color.BLACK);
				} else {
					model.setFontColor(getResources().getColor(
							R.color.categroy_title));
				}
				model.setItemColor(Color.WHITE);
			}
			mModels.get(0).setFontColor(Color.BLACK);
			TextView textView = (TextView) view.findViewById(resid);
			textView.setTextColor(getResources().getColor(listClickColor));
			// mModels.get(position).setFontColor(
			// getResources().getColor(listClickColor));
			// 原有版本改变为黄色字体的代码，用下面代码替换，不改变字体颜色
			mModels.get(position).setFontColor(Color.BLACK);
			mModels.get(position).setItemColor(
					getResources().getColor(R.color.grey2));
			mModelCategoryAdapter.notifyDataSetChanged();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * a-z索引监听 ClassName: LetterListViewListener <br/>
	 * Function: TODO ADD FUNCTION. <br/>
	 * Reason: TODO ADD REASON. <br/>
	 */
	class LetterListViewListener implements OnTouchingLetterChangedListener {
		@Override
		public void onTouchingLetterChanged(final String s) {
			if (index != null && index.get(s) != null) {
				int position = index.get(s);
				mIndexCarListView.setSelection(position);
			}
		}

	}

	/**
	 * 品牌列表适配器 ClassName: ListAdapter <br/>
	 * Function: TODO ADD FUNCTION. <br/>
	 * Reason: TODO ADD REASON. <br/>
	 */
	class ListAdapter extends BaseAdapter {
		private ImageLoadingListener mAnimateFirstListener = new AnimateFirstDisplayListener();
		private LayoutInflater inflater;
		private List<Map<String, Object>> list;

		public ListAdapter(Context context, List<Map<String, Object>> list) {
			this.inflater = LayoutInflater.from(context);
			this.list = list;
			Map<String, Integer> alphaIndexer = new HashMap<String, Integer>();
			sections = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				// 当前汉语拼音首字母
				String currentStr = list.get(i).get("Sort").toString();
				// 上一个汉语拼音首字母，如果不存在为“ ”
				String previewStr = (i - 1) >= 0 ? list.get(i - 1).get("Sort")
						.toString() : " ";
				if (!previewStr.equals(currentStr)) {
					String name = list.get(i).get("Sort").toString();
					alphaIndexer.put(name, i);
					System.out
							.println("name is " + name + ", position is " + i);
					sections[i] = name;
				}
			}
			index = (HashMap<String, Integer>) alphaIndexer;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(com.jzg.jzgcarchoose.R.layout.jzgcarchoose_car_list_content, null);
				holder = new ViewHolder();
				holder.iamge = (ImageView) convertView
						.findViewById(R.id.car_image);
				holder.name = (TextView) convertView
						.findViewById(R.id.car_name);
				holder.alpha = (TextView) convertView.findViewById(R.id.alpha);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			convertView.setBackgroundColor((Integer) list.get(position).get(
					"itemColor"));
			holder.name.setText(list.get(position).get("name").toString());
			holder.name.setTextColor((Integer) list.get(position).get(
					"fontColor"));

			String imgUrl = (String) list.get(position).get("logo");
			holder.iamge.setVisibility(View.VISIBLE);
			if (!TextUtils.isEmpty(imgUrl)) {
				// 品牌logo异步加载
				imageLoader.displayImage(imgUrl, holder.iamge, mOptions,
						mAnimateFirstListener);
			} else {
				holder.iamge.setVisibility(View.GONE);
			}

			String currentStr = list.get(position).get("Sort").toString();
			String previewStr = (position - 1) >= 0 ? list.get(position - 1)
					.get("Sort").toString() : "";
			if (!previewStr.equals(currentStr)) {
				holder.alpha.setVisibility(View.VISIBLE);
				holder.alpha.setText(currentStr);
			} else {
				holder.alpha.setVisibility(View.GONE);
			}
			return convertView;
		}

		private class ViewHolder {
			ImageView iamge;
			TextView name;
			TextView alpha;
		}

	}

	@Override
	public void onDrawerClosed() {

		// 如果mCarTypeDrawer关闭，但是mCarYearstyleDrawer还开启着，则mCarYearstyleDrawer也关联关闭
		if (!mCarTypeDrawer.isOpened() && mCarYearstyleDrawer.isOpened()) {
			mCarYearstyleDrawer.close();
		}
		// 如果CarYearstyleDrawer关闭，则调整handle的初始宽度为0
		LayoutParams layout = new LayoutParams(0,
				LayoutParams.MATCH_PARENT);
		if (!mCarYearstyleDrawer.isOpened()) {
			mCarYearstyleHandle.setLayoutParams(layout);
		}

		// 如果mCarTypeDrawer关闭，则调整两个Drawer的handle的初始宽度为0
		if (!mCarTypeDrawer.isOpened()) {
			mCarTypeHandle.setLayoutParams(layout);
			mCarYearstyleDrawer.close();
		}
	}

	@Override
	public void onDrawerOpened() {
		if (mCarTypeDrawer.isOpened()) {
			mCarTypeHandle.setLayoutParams(new LayoutParams(40,
					LayoutParams.MATCH_PARENT));
		}
		if (mCarYearstyleDrawer.isOpened()) {
			mCarYearstyleHandle.setLayoutParams(new LayoutParams(40,
					LayoutParams.MATCH_PARENT));
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// ListView开始滚动和结束滚动时候会调用
		switch (view.getId()) {
			case R.id.index_car_type_list_content:
				switch (scrollState) {
					case OnScrollListener.SCROLL_STATE_IDLE:
						break;
					case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
						if (mCarYearstyleDrawer.isOpened())
							mCarYearstyleDrawer.animateClose();
						break;
					case OnScrollListener.SCROLL_STATE_FLING:
						break;
				}
				break;
			case R.id.index_car_list:
				switch (scrollState) {
					case OnScrollListener.SCROLL_STATE_IDLE:
						// 滚动结束调用
						break;
					case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
						if (mCarTypeDrawer.isOpened())
							mCarTypeDrawer.animateClose();
						if (mCarYearstyleDrawer.isOpened())
							mCarYearstyleDrawer.animateClose();

						break;
					case OnScrollListener.SCROLL_STATE_FLING:
						break;
				}

				break;
		}

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
						 int visibleItemCount, int totalItemCount) {
		// ListView滚动过程中会一直调用
	}

	@Override
	public void onImgClick(int position) {

	}

	private CallbackForCarList mCallbackForCarList;

	public void setCallbackForCarList(CallbackForCarList callback) {
		mCallbackForCarList = callback;
	}

	public interface CallbackForCarList {
		public void toCallBackCarStyle(JzgCarChooseStyle choseStyle);

		public void toRequestCarModel(String brandId);
	}
}
