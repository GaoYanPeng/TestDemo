package com.gaoyanpeng.baidumusic.dynamic.dyadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.dynamic.DYBean;
import com.gaoyanpeng.baidumusic.tools.CircleTransform;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 高研鹏 on 2016/11/28.
 */
public class DySecondAdapter extends RecyclerView.Adapter {
    private DYBean mDYSdSecndBean;
    private Context mContext;
    private View mView;
    private RequestCreator mLoad;
    private DySecondSeVH mDySecondSeVH;
    private DySecondFirstVh mDySecondFirstVh;
    private SimpleDateFormat format = new SimpleDateFormat("MM-DD");
    private int mType;
    private String mUsePicTwo;
    private String mTime;
    private String mPicTwo;
    private String mUserNameTwo;


    public DySecondAdapter(Context context) {
        mContext = context;
    }

    public DySecondAdapter setDYSdSecndBean(DYBean DYSdSecndBean) {
        mDYSdSecndBean = DYSdSecndBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public int getItemViewType(int position) {

        int type = mDYSdSecndBean.getMsg().get(position).getMsgtype();
        if (mDYSdSecndBean.getMsg().get(position).getPiclist() != null) {
            mType = mDYSdSecndBean.getMsg().get(position).getPiclist().size();
        }
        if (mType == 2) {
            return 0;
        } else if (type == 2) {
            return 1;
        } else if (mType == 4) {
            return 2;
        } else if (mType == 6) {
            return 3;
        } else if (mType == 5) {
            return 4;
        } else if (mType == 1) {
            return 5;
        } else if (mType == 3) {
            return 6;
        } else {
            return 8;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                //2
                mView = LayoutInflater.from(mContext).inflate(R.layout.dy_se_item_item, parent, false);
                holder = new DySecondFirstVh(mView);
                break;
            case 1:
                mView = LayoutInflater.from(mContext).inflate(R.layout.dy_se_item_se_item, parent, false);
                holder = new DySecondSeVH(mView);
                break;
            //4
            case 2:
                mView = LayoutInflater.from(mContext).inflate(R.layout.dy_se_item_item_four, parent, false);
                holder = new DySecondImageFourVH(mView);
                break;
            //6
            case 3:
                mView = LayoutInflater.from(mContext).inflate(R.layout.dy_se_item_item_six, parent, false);
                holder = new DySecondImageSixVH(mView);
                break;
            //5
            case 4:
                mView = LayoutInflater.from(mContext).inflate(R.layout.dy_se_item_item_five, parent, false);
                holder = new DySecondImageFiveVH(mView);
                break;
            //1
            case 5:
                mView = LayoutInflater.from(mContext).inflate(R.layout.dy_se_item_item_one, parent, false);
                holder = new DySecondImageOneVH(mView);
                break;
            case 6:
                mView = LayoutInflater.from(mContext).inflate(R.layout.dy_se_item_item_three, parent, false);
                holder = new DySecondImageThreeVH(mView);
                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case 0:
                mDySecondFirstVh = (DySecondFirstVh) holder;
                getImageTow(position);
                break;
            case 1:
                mDySecondSeVH = (DySecondSeVH) holder;
                mLoad = Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getTopic().getPic_350x170());
                mLoad.into(mDySecondSeVH.mSePic);
                mDySecondSeVH.mTextViewSE.setText(mDYSdSecndBean.getMsg().get(position).getTopic().getTopic_title());
                break;
            //四种
            case 2:
                getImageFour((DySecondImageFourVH) holder, position);
                break;
            case 3:
                //6
                getImageSix((DySecondImageSixVH) holder, position);
                break;
            case 4:
                //5
                getImageFive((DySecondImageFiveVH) holder, position);
                break;
            case 5:
                //1
                getImagOne((DySecondImageOneVH) holder, position);
                break;
            case 6:
                getImageThree((DySecondImageThreeVH) holder, position);
                break;

        }

    }

    @Override
    public int getItemCount() {
        return 21;
    }

    //----------------------------三个
    private void getImageThree(DySecondImageThreeVH holder, int position) {
        DySecondImageThreeVH dySecondImageThreeVH = holder;
        dySecondImageThreeVH.mTContent.setText(mDYSdSecndBean.getMsg().get(position).getMsg());
        //名字
        String artistName = mDYSdSecndBean.getMsg().get(position).getAuthor().getUsername();
        if (artistName != null && !artistName.isEmpty()) {
            dySecondImageThreeVH.mTTitleName.setText(artistName);
        }
        //头像
        mUsePicTwo = mDYSdSecndBean.getMsg().get(position).getAuthor().getUserpic();
        if (mUsePicTwo != null && !mUsePicTwo.isEmpty()) {
            Picasso.with(mContext).load(mUsePicTwo).transform(new CircleTransform()).into(dySecondImageThreeVH.mTHeard);
        }
        //时间呢
        mTime = mDYSdSecndBean.getMsg().get(position).getCtime() + "";
        if (mTime != null && !mTime.isEmpty()) {
            Date date = new Date(mDYSdSecndBean.getMsg().get(position).getCtime());
            String times = format.format(date);
            dySecondImageThreeVH.mTTime.setText(times);
        }
        //图片
        if (mDYSdSecndBean.getMsg().get(position).getPiclist() != null) {
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(0).getPic_large()).into(dySecondImageThreeVH.mTOnePic);
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(1).getPic_large()).into(dySecondImageThreeVH.mTTowPic);
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(2).getPic_large()).into(dySecondImageThreeVH.mTThreePic);
        }
        mPicTwo = mDYSdSecndBean.getMsg().get(position).getContent().getPic();
        if (mPicTwo != null && !mPicTwo.isEmpty()) {
            Picasso.with(mContext).load(mPicTwo).into(dySecondImageThreeVH.mTSmallPic);
        }
        if (mDYSdSecndBean.getMsg().get(position).getTopic() != null) {
            dySecondImageThreeVH.mTTopicTitle.setText("# " + mDYSdSecndBean.getMsg().get(position).getTopic().getTopic_title());
        } else {
            dySecondImageThreeVH.mTTopicTitle.setVisibility(View.GONE);
        }
        dySecondImageThreeVH.mTMusicName.setText(mDYSdSecndBean.getMsg().get(position).getContent().getTitle());
        mUserNameTwo = mDYSdSecndBean.getMsg().get(position).getContent().getArtist_name();
        if (mUserNameTwo != null && !mUserNameTwo.isEmpty()) {
            dySecondImageThreeVH.mTMusicSName.setText(mUserNameTwo);
        }
    }


    //----------------一个图片
    private void getImagOne(DySecondImageOneVH holder, int position) {
        DySecondImageOneVH dySecondImageOneVH = holder;

        String msg = mDYSdSecndBean.getMsg().get(position).getMsg();
        if (msg != null && !msg.isEmpty()) {
            dySecondImageOneVH.mOContent.setText(msg);
        }
        //名字
        String artistName = mDYSdSecndBean.getMsg().get(position).getAuthor().getUsername();
        if (artistName != null && !artistName.isEmpty()) {
            dySecondImageOneVH.mOContent.setText(mDYSdSecndBean.getMsg().get(position).getMsg());
            dySecondImageOneVH.mOTitleName.setText(artistName);
        }
        //头像
        mUsePicTwo = mDYSdSecndBean.getMsg().get(position).getAuthor().getUserpic();
        if (mUsePicTwo != null && !mUsePicTwo.isEmpty()) {
            Picasso.with(mContext).load(mUsePicTwo).transform(new CircleTransform()).into(dySecondImageOneVH.mOHeard);
        }
        //时间呢
        mTime = mDYSdSecndBean.getMsg().get(position).getCtime() + "";
        if (mTime != null && !mTime.isEmpty()) {
            Date date = new Date(mDYSdSecndBean.getMsg().get(position).getCtime());
            String times = format.format(date);
            dySecondImageOneVH.mOTime.setText(times);
        }
        //图片
        if (mDYSdSecndBean.getMsg().get(position).getPiclist() != null) {
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(0).getPic_large()).into(dySecondImageOneVH.mOOnePic);
        }
        mPicTwo = mDYSdSecndBean.getMsg().get(position).getContent().getPic();
        if (mPicTwo != null && !mPicTwo.isEmpty()) {
            Picasso.with(mContext).load(mPicTwo).into(dySecondImageOneVH.mOSmallPic);
        }
        if (mDYSdSecndBean.getMsg().get(position).getTopic() != null) {
            dySecondImageOneVH.mOTopicTitle.setText("# " + mDYSdSecndBean.getMsg().get(position).getTopic().getTopic_title());
        } else {
            dySecondImageOneVH.mOTopicTitle.setVisibility(View.GONE);
        }
        dySecondImageOneVH.mOMusicName.setText(mDYSdSecndBean.getMsg().get(position).getContent().getTitle());
        mUserNameTwo = mDYSdSecndBean.getMsg().get(position).getContent().getArtist_name();
        if (mUserNameTwo != null && !mUserNameTwo.isEmpty()) {
            dySecondImageOneVH.mOMusicSName.setText(mUserNameTwo);
        }
    }

    private void getImageTow(int position) {
        String msg = mDYSdSecndBean.getMsg().get(position).getMsg();
        if (msg != null && !msg.isEmpty()) {
            mDySecondFirstVh.mContent.setText(msg);
        }

        //名字
        if (mDYSdSecndBean.getMsg().get(position).getContent() != null) {
            String artistName = mDYSdSecndBean.getMsg().get(position).getAuthor().getUsername();
            if (artistName != null && !artistName.isEmpty()) {
                mDySecondFirstVh.mTitleName.setText(artistName);
            }
        }
        //头像
        if (mDYSdSecndBean.getMsg().get(position).getAuthor() != null) {

            String usePicTwo = mDYSdSecndBean.getMsg().get(position).getAuthor().getUserpic();
            if (usePicTwo != null && !usePicTwo.isEmpty()) {
                Picasso.with(mContext).load(usePicTwo).transform(new CircleTransform()).into(mDySecondFirstVh.mHeard);
            }
        }
        //时间呢
        String time = mDYSdSecndBean.getMsg().get(position).getCtime() + "";
        if (time != null && !time.isEmpty()) {
            Date date = new Date(mDYSdSecndBean.getMsg().get(position).getCtime());
            String times = format.format(date);
            mDySecondFirstVh.mTime.setText(times);

        }
        //图片
        if (mDYSdSecndBean.getMsg().get(position).getPiclist() != null) {
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(0).getPic_small()).into(mDySecondFirstVh.mOnePic);
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(1).getPic_small()).into((mDySecondFirstVh.mTowPic));
        }
        if (mDYSdSecndBean.getMsg().get(position).getContent() != null) {

            String picTwo = mDYSdSecndBean.getMsg().get(position).getContent().getPic();
            if (picTwo != null && !picTwo.isEmpty()) {
                Picasso.with(mContext).load(picTwo).into(mDySecondFirstVh.mSmallPic);
            }
        }
        if (mDYSdSecndBean.getMsg().get(position).getTopic() != null) {
            mDySecondFirstVh.mTopicTitle.setText("# " + mDYSdSecndBean.getMsg().get(position).getTopic().getTopic_title());
        } else {
            mDySecondFirstVh.mTopicTitle.setVisibility(View.GONE);
        }
        if (mDYSdSecndBean.getMsg().get(position).getContent() != null) {
            mDySecondFirstVh.mMusicName.setText(mDYSdSecndBean.getMsg().get(position).getContent().getTitle());
        }
        if (mDYSdSecndBean.getMsg().get(position).getContent() !=null ){

            String userNameTwo = mDYSdSecndBean.getMsg().get(position).getContent().getArtist_name();
            if (userNameTwo != null && !userNameTwo.isEmpty()) {
                mDySecondFirstVh.mMusicSName.setText(userNameTwo);
            }
        }
    }

    //----------------四个图片
    private void getImageFour(DySecondImageFourVH holder, int position) {
        DySecondImageFourVH dySecondImageFourVH = holder;
        String msg = mDYSdSecndBean.getMsg().get(position).getMsg();
        if (msg != null && !msg.isEmpty()) {
            dySecondImageFourVH.mContentF.setText(msg);
        }
        //名字
        String artistName = mDYSdSecndBean.getMsg().get(position).getAuthor().getUsername();
        if (artistName != null && !artistName.isEmpty()) {
            dySecondImageFourVH.mTitleNameF.setText(artistName);
        }
        //头像
        mUsePicTwo = mDYSdSecndBean.getMsg().get(position).getAuthor().getUserpic();
        if (mUsePicTwo != null && !mUsePicTwo.isEmpty()) {
            Picasso.with(mContext).load(mUsePicTwo).transform(new CircleTransform()).into(dySecondImageFourVH.mHeardF);
        }
        //时间呢
        mTime = mDYSdSecndBean.getMsg().get(position).getCtime() + "";
        if (mTime != null && !mTime.isEmpty()) {
            Date date = new Date(mDYSdSecndBean.getMsg().get(position).getCtime());
            String times = format.format(date);
            dySecondImageFourVH.mTimeF.setText(times);
        }
        //图片
        if (mDYSdSecndBean.getMsg().get(position).getPiclist() != null) {
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(0).getPic_middle()).into(dySecondImageFourVH.mOnePicF);
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(1).getPic_middle()).into((dySecondImageFourVH.mTowPicF));
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(2).getPic_middle()).into((dySecondImageFourVH.mThreePicF));
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(3).getPic_middle()).into((dySecondImageFourVH.mFourPicF));
        }
        mPicTwo = mDYSdSecndBean.getMsg().get(position).getContent().getPic();
        if (mPicTwo != null && !mPicTwo.isEmpty()) {
            Picasso.with(mContext).load(mPicTwo).into(dySecondImageFourVH.mSmallPicF);
        }
        if (mDYSdSecndBean.getMsg().get(position).getTopic() != null) {
            dySecondImageFourVH.mTopicTitleF.setText("# " + mDYSdSecndBean.getMsg().get(position).getTopic().getTopic_title());
        } else {
            dySecondImageFourVH.mTopicTitleF.setVisibility(View.GONE);
        }
        dySecondImageFourVH.mMusicNameF.setText(mDYSdSecndBean.getMsg().get(position).getContent().getTitle());
        mUserNameTwo = mDYSdSecndBean.getMsg().get(position).getContent().getArtist_name();
        if (mUserNameTwo != null && !mUserNameTwo.isEmpty()) {
            dySecondImageFourVH.mMusicSNameF.setText(mUserNameTwo);
        }
    }

    //----------------五个图片
    private void getImageFive(DySecondImageFiveVH holder, int position) {
        DySecondImageFiveVH dySecondImageFiveVH = holder;
        String msg = mDYSdSecndBean.getMsg().get(position).getMsg();
        if (msg != null && !msg.isEmpty()) {
            dySecondImageFiveVH.mVContent.setText(msg);
        }
        //名字
        String artistName = mDYSdSecndBean.getMsg().get(position).getAuthor().getUsername();
        if (artistName != null && !artistName.isEmpty()) {
            dySecondImageFiveVH.mVContent.setText(mDYSdSecndBean.getMsg().get(position).getMsg());
            dySecondImageFiveVH.mVTitleName.setText(artistName);
        }
        //头像
        mUsePicTwo = mDYSdSecndBean.getMsg().get(position).getAuthor().getUserpic();
        if (mUsePicTwo != null && !mUsePicTwo.isEmpty()) {
            Picasso.with(mContext).load(mUsePicTwo).transform(new CircleTransform()).into(dySecondImageFiveVH.mVHeard);
        }
        //时间呢
        mTime = mDYSdSecndBean.getMsg().get(position).getCtime() + "";
        if (mTime != null && !mTime.isEmpty()) {
            Date date = new Date(mDYSdSecndBean.getMsg().get(position).getCtime());
            String times = format.format(date);
            dySecondImageFiveVH.mVTime.setText(times);
        }
        //图片
        if (mDYSdSecndBean.getMsg().get(position).getPiclist() != null) {
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(0).getMaster()).into(dySecondImageFiveVH.mVOnePic);
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(1).getMaster()).into(dySecondImageFiveVH.mVTowPic);
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(2).getMaster()).into(dySecondImageFiveVH.mVThreePic);
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(3).getMaster()).into(dySecondImageFiveVH.mVFourPic);
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(4).getMaster()).into(dySecondImageFiveVH.mVFivePic);
        }
        mPicTwo = mDYSdSecndBean.getMsg().get(position).getContent().getPic();
        if (mPicTwo != null && !mPicTwo.isEmpty()) {
            Picasso.with(mContext).load(mPicTwo).into(dySecondImageFiveVH.mVSmallPic);
        }
        if (mDYSdSecndBean.getMsg().get(position).getTopic() != null) {
            dySecondImageFiveVH.mVTopicTitle.setText("# " + mDYSdSecndBean.getMsg().get(position).getTopic().getTopic_title());
        } else {
            dySecondImageFiveVH.mVTopicTitle.setVisibility(View.GONE);
        }
        dySecondImageFiveVH.mVMusicName.setText(mDYSdSecndBean.getMsg().get(position).getContent().getTitle());
        mUserNameTwo = mDYSdSecndBean.getMsg().get(position).getContent().getArtist_name();
        if (mUserNameTwo != null && !mUserNameTwo.isEmpty()) {
            dySecondImageFiveVH.mVMusicSName.setText(mUserNameTwo);
        }
    }

    //----------------六个图片
    private void getImageSix(DySecondImageSixVH holder, int position) {
        DySecondImageSixVH dySecondImageSixVH = holder;
        String msg = mDYSdSecndBean.getMsg().get(position).getMsg();
        if (msg != null && !msg.isEmpty()) {
            dySecondImageSixVH.mContentX.setText(msg);
        }
        //名字
        String artistName = mDYSdSecndBean.getMsg().get(position).getAuthor().getUsername();
        if (artistName != null && !artistName.isEmpty()) {
            dySecondImageSixVH.mContentX.setText(mDYSdSecndBean.getMsg().get(position).getMsg());
            dySecondImageSixVH.mTitleNameX.setText(artistName);
        }
        //头像
        mUsePicTwo = mDYSdSecndBean.getMsg().get(position).getAuthor().getUserpic();
        if (mUsePicTwo != null && !mUsePicTwo.isEmpty()) {
            Picasso.with(mContext).load(mUsePicTwo).transform(new CircleTransform()).into(dySecondImageSixVH.mHeardX);
        }
        //时间呢
        mTime = mDYSdSecndBean.getMsg().get(position).getCtime() + "";
        if (mTime != null && !mTime.isEmpty()) {
            Date date = new Date(mDYSdSecndBean.getMsg().get(position).getCtime());
            String times = format.format(date);
            dySecondImageSixVH.mTimeX.setText(times);
        }
        //图片
        if (mDYSdSecndBean.getMsg().get(position).getPiclist() != null) {
            String picLarge = mDYSdSecndBean.getMsg().get(position).getPiclist().get(0).getMaster();
            Picasso.with(mContext).load(picLarge).into(dySecondImageSixVH.mOnePicX);
            String picLarge1 = mDYSdSecndBean.getMsg().get(position).getPiclist().get(1).getMaster();
            Picasso.with(mContext).load(picLarge1).into((dySecondImageSixVH.mTowPicX));
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(2).getPic_small()).into(dySecondImageSixVH.mThreePicX);
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(3).getPic_small()).into(dySecondImageSixVH.mFourPicX);
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(4).getPic_small()).into(dySecondImageSixVH.mFivePicX);
            Picasso.with(mContext).load(mDYSdSecndBean.getMsg().get(position).getPiclist().get(5).getPic_small()).into(dySecondImageSixVH.mSixPicX);
        }
        mPicTwo = mDYSdSecndBean.getMsg().get(position).getContent().getPic();
        if (mPicTwo != null && !mPicTwo.isEmpty()) {
            Picasso.with(mContext).load(mPicTwo).into(dySecondImageSixVH.mSmallPicX);
        }
        if (mDYSdSecndBean.getMsg().get(position).getTopic() != null) {
            dySecondImageSixVH.mTopicTitleX.setText("# " + mDYSdSecndBean.getMsg().get(position).getTopic().getTopic_title());
        } else {
            dySecondImageSixVH.mTopicTitleX.setVisibility(View.GONE);
        }
        dySecondImageSixVH.mMusicNameX.setText(mDYSdSecndBean.getMsg().get(position).getContent().getTitle());
        mUserNameTwo = mDYSdSecndBean.getMsg().get(position).getContent().getArtist_name();
        if (mUserNameTwo != null && !mUserNameTwo.isEmpty()) {
            dySecondImageSixVH.mMusicSNameX.setText(mUserNameTwo);
        }
    }

    //-------------------------换缓存类--------------------------------------------------

    class DySecondFirstVh extends RecyclerView.ViewHolder {
        private TextView mContent, mTitleName, mTime, mTopicTitle, mMusicName, mMusicSName;
        private ImageView mOnePic, mTowPic, mHeard, mSmallPic;

        public DySecondFirstVh(View itemView) {
            super(itemView);
            mHeard = (ImageView) itemView.findViewById(R.id.dy_se_item_heard);
            mOnePic = (ImageView) itemView.findViewById(R.id.dy_item_tow_pic);
            mTowPic = (ImageView) itemView.findViewById(R.id.dy_item_tows_pic);
            mContent = (TextView) itemView.findViewById(R.id.se_item_content);
            mTitleName = (TextView) itemView.findViewById(R.id.de_se_item_title_name);
            mTime = (TextView) itemView.findViewById(R.id.de_se_item_title_time);
            mTopicTitle = (TextView) itemView.findViewById(R.id.dy_se_item_topic_title);
            mSmallPic = (ImageView) itemView.findViewById(R.id.dy_item_se_se_pi_x);
            mMusicName = (TextView) itemView.findViewById(R.id.dy_se_item_music_name_N);
            mMusicSName = (TextView) itemView.findViewById(R.id.dy_se_item_music_title_name);
        }
    }

    //-------------------一张
    class DySecondImageOneVH extends RecyclerView.ViewHolder {
        private TextView mOContent, mOTitleName, mOTime, mOTopicTitle, mOMusicName, mOMusicSName;
        private ImageView mOOnePic, mOHeard, mOSmallPic;

        public DySecondImageOneVH(View itemView) {
            super(itemView);
            mOHeard = (ImageView) itemView.findViewById(R.id.dy_se_item_heard);
            mOOnePic = (ImageView) itemView.findViewById(R.id.dy_item_tow_pic);
            mOContent = (TextView) itemView.findViewById(R.id.se_item_content);
            mOTitleName = (TextView) itemView.findViewById(R.id.de_se_item_title_name);
            mOTime = (TextView) itemView.findViewById(R.id.de_se_item_title_time);
            mOTopicTitle = (TextView) itemView.findViewById(R.id.dy_se_item_topic_title);
            mOSmallPic = (ImageView) itemView.findViewById(R.id.dy_item_se_se_pi_x);
            mOMusicName = (TextView) itemView.findViewById(R.id.dy_se_item_music_name);
            mOMusicSName = (TextView) itemView.findViewById(R.id.dy_se_item_music_title_name);
        }
    }

    //--------------------三张
    class DySecondImageThreeVH extends RecyclerView.ViewHolder {
        private TextView mTContent, mTTitleName, mTTime, mTTopicTitle, mTMusicName, mTMusicSName;
        private ImageView mTOnePic, mTTowPic, mTThreePic, mTHeard, mTSmallPic;

        public DySecondImageThreeVH(View itemView) {
            super(itemView);
            mTHeard = (ImageView) itemView.findViewById(R.id.dy_se_item_heard);
            mTOnePic = (ImageView) itemView.findViewById(R.id.dy_item_one_pic);
            mTTowPic = (ImageView) itemView.findViewById(R.id.dy_item_tow_p_pic);
            mTContent = (TextView) itemView.findViewById(R.id.se_item_content);
            mTTitleName = (TextView) itemView.findViewById(R.id.de_se_item_title_name);
            mTTime = (TextView) itemView.findViewById(R.id.de_se_item_title_time);
            mTTopicTitle = (TextView) itemView.findViewById(R.id.dy_se_item_topic_title);
            mTSmallPic = (ImageView) itemView.findViewById(R.id.dy_item_se_se_pi_x);
            mTMusicName = (TextView) itemView.findViewById(R.id.dy_se_item_music_name);
            mTMusicSName = (TextView) itemView.findViewById(R.id.dy_se_item_music_title_name);
            mTThreePic = (ImageView) itemView.findViewById(R.id.dy_item_three_p_pic);

        }
    }

    //--------------四个图片
    class DySecondImageFourVH extends RecyclerView.ViewHolder {
        private TextView mContentF, mTitleNameF, mTimeF, mTopicTitleF, mMusicNameF, mMusicSNameF;
        private ImageView mOnePicF, mTowPicF, mHeardF, mSmallPicF, mThreePicF, mFourPicF;

        public DySecondImageFourVH(View itemView) {
            super(itemView);
            mHeardF = (ImageView) itemView.findViewById(R.id.dy_se_item_heard);
            mOnePicF = (ImageView) itemView.findViewById(R.id.dy_item_tow_pic);
            mTowPicF = (ImageView) itemView.findViewById(R.id.dy_item_tows_pic);
            mContentF = (TextView) itemView.findViewById(R.id.se_item_content);
            mTitleNameF = (TextView) itemView.findViewById(R.id.de_se_item_title_name);
            mTimeF = (TextView) itemView.findViewById(R.id.de_se_item_title_time);
            mTopicTitleF = (TextView) itemView.findViewById(R.id.dy_se_item_topic_title);
            mSmallPicF = (ImageView) itemView.findViewById(R.id.dy_item_se_se_pi_x);
            mMusicNameF = (TextView) itemView.findViewById(R.id.dy_se_item_music_name);
            mMusicSNameF = (TextView) itemView.findViewById(R.id.dy_se_item_music_title_name);
            mThreePicF = (ImageView) itemView.findViewById(R.id.dy_item_tow_p_pic);
            mFourPicF = (ImageView) itemView.findViewById(R.id.dy_item_tows_p_pic);
        }
    }

    //-----------------五张
    class DySecondImageFiveVH extends RecyclerView.ViewHolder {
        private TextView mVContent, mVTitleName, mVTime, mVTopicTitle, mVMusicName, mVMusicSName;
        private ImageView mVOnePic, mVTowPic, mVHeard, mVSmallPic, mVThreePic, mVFourPic, mVFivePic;

        public DySecondImageFiveVH(View itemView) {
            super(itemView);
            mVHeard = (ImageView) itemView.findViewById(R.id.dy_se_item_heard);
            mVOnePic = (ImageView) itemView.findViewById(R.id.dy_item_tow_pic);
            mVTowPic = (ImageView) itemView.findViewById(R.id.dy_item_tows_pic);
            mVContent = (TextView) itemView.findViewById(R.id.se_item_content);
            mVTitleName = (TextView) itemView.findViewById(R.id.de_se_item_title_name);
            mVTime = (TextView) itemView.findViewById(R.id.de_se_item_title_time);
            mVTopicTitle = (TextView) itemView.findViewById(R.id.dy_se_item_topic_title);
            mVSmallPic = (ImageView) itemView.findViewById(R.id.dy_item_se_se_pi_x);
            mVMusicName = (TextView) itemView.findViewById(R.id.dy_se_item_music_name);
            mVMusicSName = (TextView) itemView.findViewById(R.id.dy_se_item_music_title_name);
            mVThreePic = (ImageView) itemView.findViewById(R.id.dy_item_tows_thrree_pic);
            mVFourPic = (ImageView) itemView.findViewById(R.id.dy_item_tow_p_pic);
            mVFivePic = (ImageView) itemView.findViewById(R.id.dy_item_tows_p_pic);

        }
    }

    //-------------------六张
    class DySecondImageSixVH extends RecyclerView.ViewHolder {

        private TextView mContentX, mTitleNameX, mTimeX, mTopicTitleX, mMusicNameX, mMusicSNameX;
        private ImageView mOnePicX, mTowPicX, mHeardX, mSmallPicX, mThreePicX, mFourPicX, mFivePicX, mSixPicX;

        public DySecondImageSixVH(View itemView) {
            super(itemView);
            mHeardX = (ImageView) itemView.findViewById(R.id.dy_se_item_heard);
            mOnePicX = (ImageView) itemView.findViewById(R.id.dy_item_tow_pic);
            mTowPicX = (ImageView) itemView.findViewById(R.id.dy_item_tows_pic);
            mContentX = (TextView) itemView.findViewById(R.id.se_item_content);
            mTitleNameX = (TextView) itemView.findViewById(R.id.de_se_item_title_name);
            mTimeX = (TextView) itemView.findViewById(R.id.de_se_item_title_time);
            mTopicTitleX = (TextView) itemView.findViewById(R.id.dy_se_item_topic_title);
            mSmallPicX = (ImageView) itemView.findViewById(R.id.dy_item_se_se_pi_x);
            mMusicNameX = (TextView) itemView.findViewById(R.id.dy_se_item_music_name);
            mMusicSNameX = (TextView) itemView.findViewById(R.id.dy_se_item_music_title_name);
            mThreePicX = (ImageView) itemView.findViewById(R.id.dy_item_tows_thrree_pic);
            mFourPicX = (ImageView) itemView.findViewById(R.id.dy_item_tow_p_pic);
            mFivePicX = (ImageView) itemView.findViewById(R.id.dy_item_tows_p_pic);
            mSixPicX = (ImageView) itemView.findViewById(R.id.dy_item_tows_pp_pic);
        }
    }


    //只有一张图片
    class DySecondSeVH extends RecyclerView.ViewHolder {
        private ImageView mSePic;
        private TextView mTextViewSE;

        public DySecondSeVH(View itemView) {
            super(itemView);
            mSePic = (ImageView) itemView.findViewById(R.id.dy_item_se_se_pic);
            mTextViewSE = (TextView) itemView.findViewById(R.id.se_second_tv);
        }
    }


}
