package com.samarthgupta.sfa_app.Activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samarthgupta.sfa_app.POJO.Notice;
import com.samarthgupta.sfa_app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class HomeNoticeAdapter extends RecyclerView.Adapter<HomeNoticeAdapter.NoticeHolder> {
    private Notice notices[];

    public HomeNoticeAdapter(Notice[] notices) {
        this.notices = notices;
    }

    @Override
    public NoticeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoticeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_notice_display, parent, false));
    }

    @Override
    public void onBindViewHolder(NoticeHolder holder, int position) {

        String delDate = notices[notices.length-position-1].getDate();
        SimpleDateFormat sdfPosted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.UK);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE,dd MMM yyyy", Locale.UK);

        try {
            java.util.Date date = sdfPosted.parse(delDate);
            delDate = sdf.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.tv_date.setText(delDate);
        holder.tv_title.setText(notices[notices.length-position-1].getTitle());
        holder.tv_body.setText(notices[notices.length-position-1].getBody());
        holder.tv_num.setText(notices[notices.length-position-1].getnoticeBy());
    }

    @Override
    public int getItemCount() {
        return this.notices.length;
    }

    public class NoticeHolder extends RecyclerView.ViewHolder {
        TextView tv_date, tv_title, tv_body, tv_num;

        public NoticeHolder(View itemView) {
            super(itemView);
            tv_date = (TextView) itemView.findViewById(R.id.tv_notice_resp_date);
            tv_title = (TextView) itemView.findViewById(R.id.tv_notice_title);
            tv_body = (TextView) itemView.findViewById(R.id.tv_notice_body);
            tv_num = (TextView) itemView.findViewById(R.id.tv_notice_num);
        }
    }


}

