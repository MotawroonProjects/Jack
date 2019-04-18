package com.creativeshare.mrsool.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.creativeshare.mrsool.R;
import com.creativeshare.mrsool.models.MessageModel;
import com.creativeshare.mrsool.tags.Tags;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class ChatAdapter extends RecyclerView.Adapter {

    private final int ITEM_MESSAGE_LEFT = 1;
    private final int ITEM_MESSAGE_RIGHT = 2;
    private final int ITEM_MESSAGE_IMAGE_LEFT = 3;
    private final int ITEM_MESSAGE_IMAGE_RIGHT = 4;

    private List<MessageModel> messageModelList;
    private String current_user_id;
    private String chat_user_image;
    private Context context;

    public ChatAdapter(List<MessageModel> messageModelList, String current_user_id, String chat_user_image, Context context) {
        this.messageModelList = messageModelList;
        this.current_user_id = current_user_id;
        this.chat_user_image = chat_user_image;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_MESSAGE_LEFT) {

            View view = LayoutInflater.from(context).inflate(R.layout.chat_message_left_row, parent, false);
            return new MsgLeftHolder(view);

        } else {

            View view = LayoutInflater.from(context).inflate(R.layout.chat_message_right_row, parent, false);
            return new MsgRightHolder(view);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MessageModel messageModel = messageModelList.get(holder.getAdapterPosition());
        if (holder instanceof MsgLeftHolder) {
            MsgLeftHolder msgLeftHolder = (MsgLeftHolder) holder;
            msgLeftHolder.BindData(messageModel);

        } else if (holder instanceof MsgRightHolder) {
            MsgRightHolder msgRightHolder = (MsgRightHolder) holder;
            msgRightHolder.BindData(messageModel);
        }

    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public class MsgLeftHolder extends RecyclerView.ViewHolder {
        private CircleImageView image;
        private TextView tv_message_content, tv_time;

        public MsgLeftHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            tv_message_content = itemView.findViewById(R.id.tv_message_content);
            tv_time = itemView.findViewById(R.id.tv_time);

        }


        public void BindData(MessageModel messageModel) {

            Picasso.with(context).load(Tags.IMAGE_URL + chat_user_image).placeholder(R.drawable.logo_only).fit().priority(Picasso.Priority.HIGH).into(image);
            tv_message_content.setText(messageModel.getMessage());

            Paper.init(context);
            String lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa", new Locale(lang));
            String msg_time = dateFormat.format(new Date(Long.parseLong(messageModel.getDate()) * 1000));
            tv_time.setText(msg_time);
        }
    }

    public class MsgRightHolder extends RecyclerView.ViewHolder {
        private TextView tv_message_content, tv_time;

        public MsgRightHolder(View itemView) {
            super(itemView);
            tv_message_content = itemView.findViewById(R.id.tv_message_content);
            tv_time = itemView.findViewById(R.id.tv_time);
        }

        public void BindData(MessageModel messageModel) {

            tv_message_content.setText(messageModel.getMessage());

            Paper.init(context);
            String lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa", new Locale(lang));
            String msg_time = dateFormat.format(new Date(Long.parseLong(messageModel.getDate()) * 1000));
            tv_time.setText(msg_time);
        }
    }

    public class ImageLeftHolder extends RecyclerView.ViewHolder {
        private CircleImageView image;
        private ImageView image_bill;
        private TextView tv_message_content, tv_time;

        public ImageLeftHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            tv_message_content = itemView.findViewById(R.id.tv_message_content);
            tv_time = itemView.findViewById(R.id.tv_time);
            image_bill = itemView.findViewById(R.id.image_bill);
        }


        public void BindData(MessageModel messageModel) {

            Picasso.with(context).load(Tags.IMAGE_URL + chat_user_image).placeholder(R.drawable.logo_only).fit().priority(Picasso.Priority.HIGH).into(image);
            tv_message_content.setText(messageModel.getMessage());

            Paper.init(context);
            String lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa", new Locale(lang));
            String msg_time = dateFormat.format(new Date(Long.parseLong(messageModel.getDate()) * 1000));
            tv_time.setText(msg_time);
        }
    }


    public class ImageRightHolder extends RecyclerView.ViewHolder {
        private TextView tv_message_content, tv_time;

        public ImageRightHolder(View itemView) {
            super(itemView);
            tv_message_content = itemView.findViewById(R.id.tv_message_content);
            tv_time = itemView.findViewById(R.id.tv_time);
        }

        public void BindData(MessageModel messageModel) {

            tv_message_content.setText(messageModel.getMessage());

            Paper.init(context);
            String lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa", new Locale(lang));
            String msg_time = dateFormat.format(new Date(Long.parseLong(messageModel.getDate()) * 1000));
            tv_time.setText(msg_time);
        }
    }


    @Override
    public int getItemViewType(int position) {
        MessageModel messageModel = messageModelList.get(position);
        if (messageModel.getTo_user().equals(current_user_id)) {
            return ITEM_MESSAGE_LEFT;
        } else {
            return ITEM_MESSAGE_RIGHT;
        }





    }
}