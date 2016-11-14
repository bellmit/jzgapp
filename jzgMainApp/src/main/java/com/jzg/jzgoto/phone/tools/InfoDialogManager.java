package com.jzg.jzgoto.phone.tools;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;


public class InfoDialogManager {
	private static InfoDialogManager instance;

	private InfoDialog dialog;
	private Context context;

	private InfoDialogManager(Context context) {
		this.context = context;
	}

	public static InfoDialogManager getInstance(Context context) {
		if (instance == null) {
			instance = new InfoDialogManager(context);
		}
		instance.context = context;
		return instance;
	}

	public Dialog showDialog(boolean isShowProgress, String text) {
		try {
			if (dialog != null) {
				try {
					if (dialog.isShowing()) {
						dialog.dismiss();
					}
				} catch (Exception e) {
				}
				dialog = null;
			}
			dialog = new InfoDialog(context, R.style.info_dialog);
			dialog.updateInfo(isShowProgress, text);
			dialog.setCanceledOnTouchOutside(false);
			dialog.setCancelable(false);
			dialog.show();
			return dialog;
		} catch (Throwable e) {
		}
		return null;
	}

	public void dismissDialog() {
		try {
			if (dialog != null && dialog.isShowing()) {
				dialog.dismiss();
			}
		} catch (Exception e) {
			dialog = null;
		}
	}

	public void resetDialog() {
		dialog = null;
	}

	public class InfoDialog extends Dialog {
		private View progressBar;
		private TextView infoTextView;

		public InfoDialog(Context context) {
			super(context);
			init(context);
		}

		public InfoDialog(Context context, int theme) {
			super(context, theme);
			init(context);
		}

		private void init(Context context) {
			this.setContentView(R.layout.dialog_info_dialog_layout);
			this.progressBar = this.findViewById(R.id.loading_progressbar);
			this.infoTextView = (TextView) this.findViewById(R.id.loaded_message_textview);
		}

		public void updateInfo(boolean isShowProgress, String text) {
			this.progressBar.setVisibility(isShowProgress ? View.VISIBLE : View.GONE);
			this.infoTextView.setText(text);
		}
	}
}