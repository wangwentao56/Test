package com.example.test65.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.test65.R;
import com.example.test65.adpater.RlvShowAdapter;
import com.example.test65.base.BaseActivity;
import com.example.test65.base.Constants;
import com.example.test65.bean.GoldShowBean;
import com.example.test65.presenter.EmptyP;
import com.example.test65.view.EmptyV;
import com.example.test65.weight.SimpleTouchHelperCallBack;

import java.util.ArrayList;

import butterknife.BindView;

public class ShowActivity extends BaseActivity<EmptyV,EmptyP>implements EmptyV{
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private ArrayList<GoldShowBean> mList;
    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        mList = (ArrayList<GoldShowBean>) getIntent().getSerializableExtra(Constants.DATA);

        mToolBar.setTitle(R.string.special_show);
        mToolBar.setNavigationIcon(R.drawable.ic_close);
        setSupportActionBar(mToolBar);

                mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });

        mRlv.setLayoutManager(new LinearLayoutManager(this));
        RlvShowAdapter adapter = new RlvShowAdapter(mList);
        mRlv.setAdapter(adapter);
        mRlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //拖拽移动和侧滑删除的功能
        SimpleTouchHelperCallBack simpleTouchHelperCallBack =
                new SimpleTouchHelperCallBack(adapter);

        simpleTouchHelperCallBack.setSwipeEnable(true);

        ItemTouchHelper helper = new ItemTouchHelper(simpleTouchHelperCallBack);

        helper.attachToRecyclerView(mRlv);
    }

    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA, mList);
        setResult(RESULT_OK,intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        finishAct();
    }
}
