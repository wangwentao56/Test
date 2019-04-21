package com.example.test65.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.test65.R;
import com.example.test65.base.BaseActivity;
import com.example.test65.presenter.LoginP;
import com.example.test65.utils.ToastUtil;
import com.example.test65.view.LoginView;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity<LoginView,LoginP>implements LoginView {
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_psd)
    EditText mEtPsd;

    @Override
    protected LoginP initPresenter() {
        return new LoginP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.btn})
    public void click(View v){
        //每次转换类型,麻烦
        //((LoginP)mPresenter).getData();
        //mPresenter.getData();
        mPresenter.login();
    }

    @Override
    public void setData(String data) {
        mBtn.setText(data);
    }

    @Override
    public String getUserName() {
        return mEtName.getText().toString().trim();
    }

    @Override
    public String getPsd() {
        return mEtPsd.getText().toString().trim();
    }

    @Override
    public void showToast(String msg) {
        //Handler
        //runOnUiThread()
        //View.post()
        ToastUtil.showShort(msg);
    }
}
