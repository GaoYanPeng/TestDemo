package com.gaoyanpeng.musicbaidu.music.musicfragment.recommended;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.music.musicfragment.recommended.itemadapter.RECFirstEightAdapter;
import com.gaoyanpeng.musicbaidu.music.musicfragment.recommended.itemadapter.RECFirstFiveAdapter;
import com.gaoyanpeng.musicbaidu.music.musicfragment.recommended.itemadapter.RECFirstOneAdapter;
import com.gaoyanpeng.musicbaidu.music.musicfragment.recommended.itemadapter.RECFirstSevenAdapter;
import com.gaoyanpeng.musicbaidu.music.musicfragment.recommended.itemadapter.RECFirstSixAdapter;
import com.gaoyanpeng.musicbaidu.music.musicfragment.recommended.itemadapter.RECFirstThreeAdapter;
import com.gaoyanpeng.musicbaidu.music.musicfragment.recommended.itemadapter.RECFirstTowAdapter;
import com.gaoyanpeng.musicbaidu.music.musicfragment.recommended.itemadapter.RECFourOneAdapter;
import com.gaoyanpeng.musicbaidu.music.musiconclick.AllOnclick;
import com.gaoyanpeng.musicbaidu.welcome.WEActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;

/**
 * Created by 高研鹏 on 2016/11/23.
 */

public class RecomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private View mViewAll;
    private RequestCreator mLoad;
    private RecommBean mRecommBean;
    private ShufflingVH mShufflingVH;
    private LinearLayoutManager mManager;
    private GridLayoutManager mGridManager;
    private FirstViewHolder mFirstAllViewHolder;
    private FourViewHolder mFourViewHolder;
    private ThirdViewHolder mThirdOneViewHolder;
    private SecondViewHolder mSecondViewHolder;
    private RECFourOneAdapter mRECFourOneAdapter;
    private RECFirstSixAdapter mRecFirstSixAdapter;
    private RECFirstOneAdapter mRecFirstOneAdapter;
    private RECFirstTowAdapter mRecFirstTowAdapter;
    private RECFirstFiveAdapter mRecFirstFiveAdapter;
    private RECFirstSevenAdapter mRecFirstSevenAdapter;
    private RECFirstEightAdapter mRecFirstEightAdapter;
    private RECFirstThreeAdapter mRecFirstThreeAdapter;
    private FragmentManager mFragmentManager;
    public static  final String OpenOneFm = "openOne";

    public RecomAdapter setFragmentManager(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
        return this;
    }

    public RecomAdapter(Context context) {
        mContext = context;
    }

    public RecomAdapter setRecommBean(RecommBean recommBean) {
        mRecommBean = recommBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                mViewAll = LayoutInflater.from(mContext).inflate(R.layout.shuff_item_item, parent, false);
                holder = new ShufflingVH(mViewAll);
                break;
            case 1:
                mViewAll = LayoutInflater.from(mContext).inflate(R.layout.recom_four_item, parent, false);
                holder = new FourViewHolder(mViewAll);
                break;
            case 2:
                mViewAll = LayoutInflater.from(mContext).inflate(R.layout.recom_first_item, parent, false);
                holder = new FirstViewHolder(mViewAll);
                break;
            case 3:
                mViewAll = LayoutInflater.from(mContext).inflate(R.layout.recom_first_item, parent, false);
                holder = new FirstViewHolder(mViewAll);
                break;
            case 4:
                mViewAll = LayoutInflater.from(mContext).inflate(R.layout.recom_first_item, parent, false);
                holder = new FirstViewHolder(mViewAll);
                break;
            case 5:
                mViewAll = LayoutInflater.from(mContext).inflate(R.layout.recom_second_item, parent, false);
                holder = new SecondViewHolder(mViewAll);
                break;
            case 6:
                mViewAll = LayoutInflater.from(mContext).inflate(R.layout.recom_first_item, parent, false);
                holder = new FirstViewHolder(mViewAll);
                break;
            case 7:
                mViewAll = LayoutInflater.from(mContext).inflate(R.layout.recom_first_item, parent, false);
                holder = new FirstViewHolder(mViewAll);
                break;
            case 8:
                mViewAll = LayoutInflater.from(mContext).inflate(R.layout.recom_first_item, parent, false);
                holder = new FirstViewHolder(mViewAll);
                break;
            case 9:
                mViewAll = LayoutInflater.from(mContext).inflate(R.layout.recom_third_item, parent, false);
                holder = new ThirdViewHolder(mViewAll);
                break;
        }
        return holder;

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        //StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3)
        switch (type) {
            case 0:
                getShuffingVH((ShufflingVH) holder);
                break;
            case 1:
                getFourViewHolder((FourViewHolder) holder);
                break;
            case 2:
                getFirstOneVH((FirstViewHolder) holder);
                break;
            case 3:
                getFirstTowVH((FirstViewHolder) holder);
                break;
            case 4:
                getFirstThreeVH((FirstViewHolder) holder);
                break;
            case 5:
                getFirstFourVH((SecondViewHolder) holder);
                break;
            case 6:
                getFireFiveVH((FirstViewHolder) holder);
                break;
            case 7:
                getFirstSixVH((FirstViewHolder) holder);
                break;
            case 8:
                getFirstSevenVH((FirstViewHolder) holder);

                break;
            case 9:
                getThirdEightVH((ThirdViewHolder) holder);
                break;


        }
        Animation mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.in_lefttoright);
        holder.itemView.setAnimation(mAnimation);

    }

    //-----------------------------------轮播图----------------------------------------------------------//
    private void getShuffingVH(ShufflingVH holder) {
        mShufflingVH = holder;
        ArrayList<String> image = new ArrayList<>();
        for (int i = 0; i < mRecommBean.getResult().getFocus().getResult().size(); i++) {
            image.add(mRecommBean.getResult().getFocus().getResult().get(i).getRandpic_iphone6());
        }
        mShufflingVH.mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        // 设置图片加载器
        mShufflingVH.mBanner.setImageLoader(new GlideImageloader());
        // 设置图片集合
        mShufflingVH.mBanner.setImages(image);
        // 设置banner动画效果
        mShufflingVH.mBanner.setBannerAnimation(Transformer.DepthPage);
        mShufflingVH.mBanner.setBannerAnimation(Transformer.DepthPage);
        // 设置自动轮播 默认为true
        mShufflingVH.mBanner.isAutoPlay(true);
        // 设置轮播时间
        mShufflingVH.mBanner.setDelayTime(2000);
        // 设置指示器位置 (当banner模式中有指示器时)
        mShufflingVH.mBanner.setIndicatorGravity(BannerConfig.CENTER);
        // banner设置方法全部调用完毕时最后调用
        mShufflingVH.mBanner.start();
        mShufflingVH.mBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(mContext,WEActivity.class);
                String url = mRecommBean.getResult().getFocus().getResult().get(position-1).getCode();
                intent.putExtra("url",url);
                mContext.startActivity(intent);
            }
        });
    }


    //----------------------------第三种行布局-----第八个行布局-------------------------------------------------//
    private void getThirdEightVH(ThirdViewHolder holder) {
        mManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mThirdOneViewHolder = holder;
        mThirdOneViewHolder.title.setText(mRecommBean.getModule().get(13).getTitle());
        mLoad = Picasso.with(mContext).load(mRecommBean.getModule().get(13).getPicurl());
        mLoad.into(mThirdOneViewHolder.imageView);
        mThirdOneViewHolder.mRecyclerView.setLayoutManager(mManager);
        mRecFirstEightAdapter = new RECFirstEightAdapter(mContext, 8);
        mRecFirstEightAdapter.setRecommEightBean(mRecommBean);
        mThirdOneViewHolder.mRecyclerView.setAdapter(mRecFirstEightAdapter);

        mRecFirstEightAdapter.setAllOnclick(new AllOnclick() {
            @Override
            public void allOnclick(int pos) {
                Toast.makeText(mContext, "pos:" + pos, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,WEActivity.class);
                String recEitUrl = mRecommBean.getResult().getMod_7().getResult().get(pos).getType_id();
                intent.putExtra("recRiyUrl",recEitUrl);
                intent.putExtra("text","百度音乐独家策划");
                mContext.startActivity(intent);
            }
        });
    }

    //----------------------------第一种行布局第七个---------------------------------------------------//
    private void getFirstSevenVH(FirstViewHolder holder) {
        mGridManager = new GridLayoutManager(mContext, 3);
        mFirstAllViewHolder = holder;
        mFirstAllViewHolder.title.setText(mRecommBean.getModule().get(12).getTitle());
        mLoad = Picasso.with(mContext).load(mRecommBean.getModule().get(12).getPicurl());
        mLoad.into(mFirstAllViewHolder.imageView);
        mFirstAllViewHolder.mFirstRecyclerView.setLayoutManager(mGridManager);
        mRecFirstSevenAdapter = new RECFirstSevenAdapter(mContext, 7);
        mRecFirstSevenAdapter.setRECFirstSevenBean(mRecommBean);
        mFirstAllViewHolder.mFirstRecyclerView.setAdapter(mRecFirstSevenAdapter);
    }

    //----------------------------第一种行布局第四个---------------------------------------------------//
    private void getFirstFourVH(SecondViewHolder holder) {
        mSecondViewHolder = holder;
        mLoad = Picasso.with(mContext).load(mRecommBean.getResult().getMod_26().getResult().get(0).getPic());
        mLoad.into(mSecondViewHolder.mPic);
        return;
    }


    /**
     * 数据6
     *
     * @param holder
     */
    //----------------------------第一种行布局第六个---------------------------------------------------//
    private void getFirstSixVH(FirstViewHolder holder) {
        mGridManager = new GridLayoutManager(mContext, 3);
        mFirstAllViewHolder = holder;
        mFirstAllViewHolder.title.setText(mRecommBean.getModule().get(11).getTitle());
        mLoad = Picasso.with(mContext).load(mRecommBean.getModule().get(11).getPicurl());
        mLoad.into(mFirstAllViewHolder.imageView);
        mFirstAllViewHolder.mFirstRecyclerView.setLayoutManager(mGridManager);
        mRecFirstSixAdapter = new RECFirstSixAdapter(mContext, 6);
        mRecFirstSixAdapter.setRECFirstSixBean(mRecommBean);
        mFirstAllViewHolder.mFirstRecyclerView.setAdapter(mRecFirstSixAdapter);
    }

    /**
     * 数据5
     *
     * @param holder
     */
    //----------------------------第一种行布局第五个---------------------------------------------------//
    private void getFireFiveVH(FirstViewHolder holder) {
        mGridManager = new GridLayoutManager(mContext, 3);
        mFirstAllViewHolder = holder;
        mFirstAllViewHolder.title.setText(mRecommBean.getModule().get(10).getTitle());
        mLoad = Picasso.with(mContext).load(mRecommBean.getModule().get(10).getPicurl());
        mLoad.into(mFirstAllViewHolder.imageView);
        mFirstAllViewHolder.mFirstRecyclerView.setLayoutManager(mGridManager);
        mRecFirstFiveAdapter = new RECFirstFiveAdapter(mContext, 5);
        mRecFirstFiveAdapter.setRECFiveOneBean(mRecommBean);
        mFirstAllViewHolder.mFirstRecyclerView.setAdapter(mRecFirstFiveAdapter);
    }

    /**
     * 数据3
     *
     * @param holder
     */
    //----------------------------第一种行布局第三个---------------------------------------------------//
    private void getFirstThreeVH(FirstViewHolder holder) {
        mGridManager = new GridLayoutManager(mContext, 3);
        mFirstAllViewHolder = holder;
        mFirstAllViewHolder.title.setText(mRecommBean.getModule().get(6).getTitle());
        mLoad = Picasso.with(mContext).load(mRecommBean.getModule().get(6).getPicurl());
        mLoad.into(mFirstAllViewHolder.imageView);
        mFirstAllViewHolder.mFirstRecyclerView.setLayoutManager(mGridManager);
        mRecFirstThreeAdapter = new RECFirstThreeAdapter(mContext, 3);
        mRecFirstThreeAdapter.setRECFirstOneBean(mRecommBean);
        mFirstAllViewHolder.mFirstRecyclerView.setAdapter(mRecFirstThreeAdapter);
    }

    /**
     * 数据2
     *
     * @param holder
     */
    //----------------------------第一种行布局第二个---------------------------------------------------//
    private void getFirstTowVH(FirstViewHolder holder) {
        mGridManager = new GridLayoutManager(mContext, 3);
        mFirstAllViewHolder = holder;
        mFirstAllViewHolder.title.setText(mRecommBean.getModule().get(5).getTitle());
        mLoad = Picasso.with(mContext).load(mRecommBean.getModule().get(5).getPicurl());
        mLoad.into(mFirstAllViewHolder.imageView);
        mRecFirstTowAdapter = new RECFirstTowAdapter(mContext, 2);
        mRecFirstTowAdapter.setRECFirstTowBean(mRecommBean);
        mFirstAllViewHolder.mFirstRecyclerView.setAdapter(mRecFirstTowAdapter);
        mFirstAllViewHolder.mFirstRecyclerView.setLayoutManager(mGridManager);
    }

    /**
     * 数据1
     *
     * @param holder
     */
    //----------------------------第一种行布局第一个---------------------------------------------------//
    private void getFirstOneVH(FirstViewHolder holder) {
        mGridManager = new GridLayoutManager(mContext, 3);
        mFirstAllViewHolder = holder;
        mFirstAllViewHolder.title.setText(mRecommBean.getModule().get(3).getTitle());
        mLoad = Picasso.with(mContext).load(mRecommBean.getModule().get(3).getPicurl());
        mLoad.into(mFirstAllViewHolder.imageView);
        mRecFirstOneAdapter = new RECFirstOneAdapter(mContext, 1);
        mFirstAllViewHolder.mFirstRecyclerView.setLayoutManager(mGridManager);
        mRecFirstOneAdapter.setRECFirstOneBean(mRecommBean);
        mFirstAllViewHolder.mFirstRecyclerView.setAdapter(mRecFirstOneAdapter);
    }

    //----------------------------第四种行布局第一个---------------------------------------------------//
    private void getFourViewHolder(FourViewHolder holder) {
        mManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mFourViewHolder = holder;
        mRECFourOneAdapter = new RECFourOneAdapter(0, mContext);
        mFourViewHolder.mRecyclerView.setLayoutManager(mManager);
        mRECFourOneAdapter.setFirstBean(mRecommBean);
        mFourViewHolder.mRecyclerView.setAdapter(mRECFourOneAdapter);
        mRECFourOneAdapter.setAllOnclick(new AllOnclick() {
            @Override
            public void allOnclick(int pos) {
                Toast.makeText(mContext, "pos:" + pos, Toast.LENGTH_SHORT).show();
                // TODO: 2016/12/6 发广播打开 Fragment
               if (pos == 0){
                    Intent intent = new Intent(OpenOneFm);
                    mContext.sendBroadcast(intent);
               }



            }
        });
    }

    /**
     * RecyclerView 的总数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 10;
    }

    /**
     * 行布局类型的位置
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        if (position == 1) {
            return 1;
        }
        if (position == 2) {
            return 2;
        }
        if (position == 3) {
            return 3;
        }
        if (position == 4) {
            return 4;
        }
        if (position == 5) {
            return 5;
        }
        if (position == 6) {
            return 6;
        }
        if (position == 7) {
            return 7;
        }
        if (position == 8) {
            return 8;
        }
        if (position == 9) {
            return 9;

        } else {
            return 10;
        }
    }



    //----------------------------第一种缓存类----------------//
    class FirstViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private RecyclerView mFirstRecyclerView;

        public FirstViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.first_item_image);
            title = (TextView) itemView.findViewById(R.id.first_item_title);
            mFirstRecyclerView = (RecyclerView) itemView.findViewById(R.id.recom_first_item_ry);
        }
    }

    //----------------------------第二种缓存类----------------//
    class SecondViewHolder extends RecyclerView.ViewHolder {
        private ImageView mPic;

        public SecondViewHolder(View itemView) {
            super(itemView);
            mPic = (ImageView) itemView.findViewById(R.id.recom_second_item_image);
        }
    }

    //----------------------------第三种缓存类----------------//
    class ThirdViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;

        private RecyclerView mRecyclerView;

        public ThirdViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.third_item_image);
            title = (TextView) itemView.findViewById(R.id.third_item_title);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recom_third_item_ry);
        }
    }

    //----------------------------第四种缓存类----------------//
    class FourViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;

        public FourViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recom_four_item_ryl);
        }
    }

    //----------------------------轮播种缓存类----------------//
    class ShufflingVH extends RecyclerView.ViewHolder {
        private Banner mBanner;

        public ShufflingVH(View itemView) {
            super(itemView);
            mBanner = (Banner) itemView.findViewById(R.id.shuff_item_vp);
        }
    }
}
//    private FirstViewHolder mFirstOneViewHolder;
//    private FirstViewHolder mFirstTowViewHolder;
//    private FirstViewHolder mFirstThreeViewHolder;