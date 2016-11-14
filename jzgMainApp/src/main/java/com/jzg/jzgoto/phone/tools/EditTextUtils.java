package com.jzg.jzgoto.phone.tools;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EditTextUtils {

	/**
	 * 估值里程
	 * 
	 * @param editText
	 * @param context
	 */
	public static void mileageTextWatcher(final EditText editText,
			final Context context) {
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				System.out.println(s);
				if (s.length() == 0) {
					return;
				}
				
				//包含小数点
				if(s.toString().contains(".")){
					
					//如果第一位为小数点
					if(".".equals(String.valueOf(s.charAt(0)))){
						editText.setText("");
						ShowDialogTool.showCenterToast(context,  "第一位不能为小数点");
						
					}else if(Double.valueOf(String.valueOf(s)) > 100){
							editText.setText("100");
							editText.setSelection("100".length());
					}
					//如果小数点后长度大于2
					else if(s.toString()
							.substring(s.toString().lastIndexOf("."))
							.length() > 3){
						editText.setText(s.toString().substring(0,s.length()-1));
						editText.setSelection(s.toString().substring(0,s.length()-1).length());
						ShowDialogTool.showCenterToast(context,  "只能输入小数点后两位");
					}else if("0".equals(String.valueOf(s.charAt(0))) &&!".".equals(String.valueOf(s.charAt(1)))){
						editText.setText("0");
						editText.setSelection(1);
					}
					
				//不包含小数点	
				}else{
					if (Integer.valueOf(String.valueOf(s)) > 100) {
						editText.setText("100");
						editText.setSelection("100".length());
						
					}else if("0".equals(String.valueOf(s.charAt(0)))){
						if (s.length() >= 2) {
							editText.setText("0");
							editText.setSelection(1);
						}
						ShowDialogTool.showCenterToast(context,  "请输入小数点");
					}
				}
				
				
				
				
				
				
				

//				if (!s.toString().contains(".")
//						&& Integer.valueOf(String.valueOf(s)) > 100) {
//					editText.setText("100");
//					editText.setSelection("100".length());
//				} else if (s.length() > 3 && !s.toString().contains(".")) {
//
//					editText.setText(s.toString()
//							.subSequence(0, s.length() - 1));
//					editText.setSelection(s.length() - 1);
//					ShowDialogTool.showCenterToast(context,  "请输入小数点");
//				} else if (s.toString().contains(".")
//						&& s.toString()
//								.substring(s.toString().lastIndexOf("."))
//								.length() > 3) {
//					editText.setText(s.toString().substring(0,
//							s.toString().lastIndexOf("."))
//							+ s.toString()
//									.substring(s.toString().lastIndexOf("."))
//									.subSequence(0, 3));
//					editText.setSelection(s.length() - 1);
//					ShowDialogTool.showCenterToast(context,  "只能输入小数点后两位");
//				} else if ("0".equals(String.valueOf(s.charAt(0)))
//						&& !s.toString().contains(".")) {
//					if (s.length() == 2
//							&& "0".equals(String.valueOf(s.charAt(0)))) {
//						editText.setText("0");
//						editText.setSelection(1);
//					}
//					ShowDialogTool.showCenterToast(context,  "请输入小数点");
//				} else if (".".equals(String.valueOf(s.charAt(0)))) {
//					editText.setText("");
//					ShowDialogTool.showCenterToast(context,  "第一位不能为小数点");
//				}else if (s.toString().contains(".")
//						&& Float.valueOf(String.valueOf(s)) > 100) {
//					editText.setText("100");
//					editText.setSelection("100".length());
//				}else if ("0".equals(String.valueOf(s.charAt(0)))
//						&&!".".equals(String.valueOf(s.charAt(1)))) {
//					if(s.toString().contains(".")){
//						editText.setText(Double.valueOf(String.valueOf(s)).toString());
//					}else{
//						editText.setText(Integer.valueOf(String.valueOf(s)).toString());
//					}
//					editText.setSelection(editText.getText().length());
//				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				editText.setSelection(s.length());

			}

			@Override
			public void afterTextChanged(Editable s) {
				

			}
		});
	}

	/**
	 * 估值 开票价格
	 * 
	 * @param editText
	 * @param context
	 */
	public static void priceWatcher(final EditText editText, final Context context) {
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				System.out.println(s);
				if (s.length() == 0) {
					return;
				}
				
				//包含小数点
				if(s.toString().contains(".")){
					
					//如果第一位为小数点
					if(".".equals(String.valueOf(s.charAt(0)))){
						editText.setText("");
						ShowDialogTool.showCenterToast(context,  "第一位不能为小数点");
						
					}else if(Double.valueOf(String.valueOf(s)) > 1000){
							editText.setText("1000");
							editText.setSelection("1000".length());
					}
					//如果小数点后长度大于2
					else if(s.toString()
							.substring(s.toString().lastIndexOf("."))
							.length() > 3){
						editText.setText(s.toString().substring(0,s.length()-1));
						editText.setSelection(s.toString().substring(0,s.length()-1).length());
						ShowDialogTool.showCenterToast(context,  "只能输入小数点后两位");
					}else if("0".equals(String.valueOf(s.charAt(0))) &&!".".equals(String.valueOf(s.charAt(1)))){
						editText.setText("0");
						editText.setSelection(1);
					}
					
				//不包含小数点	
				}else{
					if (Integer.valueOf(String.valueOf(s)) > 1000) {
						editText.setText("1000");
						editText.setSelection("1000".length());
					}else if("0".equals(String.valueOf(s.charAt(0)))){
						if (s.length() >= 2) {
							editText.setText("0");
							editText.setSelection(1);
						}
						ShowDialogTool.showCenterToast(context,  "请输入小数点");
					}
				}
				
				
//				
//				if (!s.toString().contains(".")
//						&& Double.valueOf(String.valueOf(s)) > 10000) {
//					editText.setText("10000");
//					editText.setSelection("10000".length());
//				} else if (s.length() > 5 && !s.toString().contains(".")) {
//
//					editText.setText(s.toString().subSequence(0,
//							s.length() - 1));
//					editText.setSelection(s.length() - 1);
//					ShowDialogTool.showCenterToast(context,  "请输入小数点");
//				} else if (s.toString().contains(".")
//						&& s.toString()
//								.substring(s.toString().lastIndexOf("."))
//								.length() > 3) {
//					editText.setText(s.toString().substring(0,
//							s.toString().lastIndexOf("."))
//							+ s.toString()
//									.substring(s.toString().lastIndexOf("."))
//									.subSequence(0, 3));
//					editText.setSelection(s.length() - 1);
//					// Toast.makeText(getActivity(), "只能输入小数点后两位", 2000).show();
//				} else if ("0".equals(String.valueOf(s.charAt(0)))
//						&& !s.toString().contains(".")) {
//					if (s.length() == 2
//							&& "0".equals(String.valueOf(s.charAt(0)))) {
//						editText.setText("0");
//						editText.setSelection(1);
//					}
//				ShowDialogTool.showCenterToast(context, "请输入小数点" );
//				} else if (".".equals(String.valueOf(s.charAt(0)))) {
//					editText.setText("");
//					ShowDialogTool.showCenterToast(context, "第一位不能为小数点" );
//				}else if (s.toString().contains(".")
//						&& Double.valueOf(String.valueOf(s)) > 10000) {
//					editText.setText("10000");
//					editText.setSelection("10000".length());
//				} 

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				editText.setSelection(s.length());
			}

			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
	}
}
