package com.jzg.jzgoto.phone.view.error;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;


public class DefaultErrorView extends BaseErrorView {
    private View contentContainer;
    private View errorContainer;
    private ImageView errorImage;
    private TextView text;
    private Button refreshButton;
    private View loadingView;
    private View mEmptyView;

    public DefaultErrorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public DefaultErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DefaultErrorView(Context context) {
        super(context);
        init(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        // if (this.loadingView != null) {
        // this.loadingView.stopAnim();
        // }
        super.onDetachedFromWindow();
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_shared_error_defaulterrorview, this, true);
        this.contentContainer = this.findViewById(R.id.errorview_content_container);
        this.errorContainer = this.findViewById(R.id.errorview_error_container);
        this.errorImage = (ImageView) this.findViewById(R.id.errorview_image);
        this.text = (TextView) this.findViewById(R.id.errorview_text);
        this.loadingView = this.findViewById(R.id.errorview_loading_container);
        this.refreshButton = (Button) this.findViewById(R.id.errorview_button);
        this.mEmptyView = this.findViewById(R.id.empty_view);
        this.refreshButton.setOnClickListener(refreshListener);
        this.errorImage.setOnClickListener(refreshListener);
    }

    @Override
    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
        updateViews();
    }

    private void updateViews() {
        this.mEmptyView.setVisibility(View.GONE);
        this.loadingView.setVisibility(View.GONE);
        this.errorContainer.setVisibility(View.GONE);
        switch (this.errorType) {
            case NetworkNotAvailable:
                // this.loadingView.stopAnim();
                this.errorContainer.setVisibility(View.VISIBLE);
                this.text.setText(R.string.view_shared_errorview_message_networknotavailable);
                this.errorImage.setImageResource(R.drawable.nonetwork_icon);
                this.refreshButton.setVisibility(View.VISIBLE);
                // this.contentContainer.setOnClickListener(refreshListener);
                break;
            case NotFound:
            case NoData:
                this.mEmptyView.setVisibility(View.VISIBLE);
                this.contentContainer.setOnClickListener(null);
                break;
            case Loading:
                this.loadingView.setVisibility(View.VISIBLE);
                // this.loadingView.startAnim();
                // this.refreshButton.setVisibility(View.INVISIBLE);
                this.contentContainer.setOnClickListener(null);
                break;
            case Error:
                // this.loadingView.stopAnim();
                this.errorContainer.setVisibility(View.VISIBLE);
                this.text.setText(R.string.view_shared_errorview_message_error);
                this.errorImage.setImageResource(R.drawable.nonetwork_icon);
                this.refreshButton.setVisibility(View.VISIBLE);
                // this.contentContainer.setOnClickListener(refreshListener);
                break;
            default:
                break;
        }
    }

    OnClickListener refreshListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (errorlistener != null && (errorType == ErrorType.Error || errorType == ErrorType.NetworkNotAvailable)) {
                errorlistener.OnErrorRefresh();
            }
        }
    };
}
