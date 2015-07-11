package com.iwoll.editviewdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.iwoll.editviewdemo.view.PaymentEditText;

public class PaymentDialog extends DialogFragment{

    private PaymentListener listener;
    private PaymentEditText passwordEt;
    private Handler mHandler;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (PaymentListener) activity;//支付成功回调
        mHandler = new Handler();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_payment,null);
        builder.setView(view);
        final Button conform_btn = (Button) view.findViewById(R.id.conform);
        Button cancle_btn = (Button) view.findViewById(R.id.cancle);
        passwordEt = (PaymentEditText) view .findViewById(R.id.payment_password_ed);
        final TextView passwordErrorTipsTv = (TextView) view.findViewById(R.id.password_error_tips_tv);

//        passwordEt.setFocusable(true);
//        passwordEt.setFocusableInTouchMode(true);
//        passwordEt.requestFocus();
////        //弹出软键盘
//        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInputFromInputMethod(passwordEt.getWindowToken(), 0);
        //确认监听
        conform_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = passwordEt.getText().toString();
                if(password.equals("123456")){
                    dismiss();
                    listener.callback("支付成功");
                }else passwordErrorTipsTv.setVisibility(View.VISIBLE);
            }
        });

        //取消监听
        cancle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        dismiss();

            }
        });

        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordErrorTipsTv.setVisibility(View.INVISIBLE);

                Log.i(s.toString(), "   "+s.length() +"   start=="+start+"   before=="+before);
                passwordEt.changePostion(s.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //延迟弹出软键盘
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(passwordEt, 0);
            }
        },160) ;

        return builder.create();
    }
}
