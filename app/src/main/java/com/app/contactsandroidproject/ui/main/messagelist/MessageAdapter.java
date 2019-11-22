/**
 * Created by narendra on 16/11/2019.
 */

package com.app.contactsandroidproject.ui.main.messagelist;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.app.contactsandroidproject.data.model.db.Contact;
import com.app.contactsandroidproject.databinding.ItemMessageEmptyViewBinding;
import com.app.contactsandroidproject.databinding.ItemMessageListBinding;
import com.app.contactsandroidproject.ui.main.base.BaseViewHolder;
import com.app.contactsandroidproject.utils.AppLogger;

import java.util.List;

/**
 * Created by narendra on 19/11/19.
 */

public class MessageAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Contact> mMessageResponseList;

    private MessageAdapterListener mListener;

    public MessageAdapter(List<Contact> mContactResponseList) {
        this.mMessageResponseList = mContactResponseList;
    }

    @Override
    public int getItemCount() {
        if (mMessageResponseList != null && mMessageResponseList.size() > 0) {
            return mMessageResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mMessageResponseList != null && !mMessageResponseList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemMessageListBinding messageListBinding = ItemMessageListBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new MessageViewHolder(messageListBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemMessageEmptyViewBinding emptyViewBinding = ItemMessageEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<Contact> contactList) {
        mMessageResponseList.addAll(contactList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mMessageResponseList.clear();
    }

    public void setListener(MessageAdapterListener listener) {
        this.mListener = listener;
    }

    public interface MessageAdapterListener {

        void onRetryClick();
    }

    public class MessageViewHolder extends BaseViewHolder implements MessageItemViewModel.ContactItemViewModelListener {

        private ItemMessageListBinding mBinding;

        private MessageItemViewModel mMessageItemViewModel;

        public MessageViewHolder(ItemMessageListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Contact contact = mMessageResponseList.get(position);
            mMessageItemViewModel = new MessageItemViewModel(contact, this);
            mBinding.setViewModel(mMessageItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(String blogUrl) {
            if (blogUrl != null) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(blogUrl));
                    itemView.getContext().startActivity(intent);
                } catch (Exception e) {
                    AppLogger.d("url error");
                }
            }
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements MessageEmptyItemViewModel.MessageEmptyItemViewModelListener {

        private ItemMessageEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemMessageEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            MessageEmptyItemViewModel emptyItemViewModel = new MessageEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }
}