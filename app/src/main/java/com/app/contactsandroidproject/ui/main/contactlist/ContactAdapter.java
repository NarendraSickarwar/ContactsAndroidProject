/**
 * Created by narendra on 16/11/2019.
 */

package com.app.contactsandroidproject.ui.main.contactlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.app.contactsandroidproject.data.model.db.Contact;
import com.app.contactsandroidproject.databinding.ItemContactEmptyViewBinding;
import com.app.contactsandroidproject.databinding.ItemContactListBinding;
import com.app.contactsandroidproject.ui.main.base.BaseViewHolder;

import java.util.List;

/**
 * Created by narendra on 19/11/19.
 */

public class ContactAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Contact> mContactResponseList;

    private ContactAdapterListener mListener;

    public ContactAdapter(List<Contact> mContactResponseList) {
        this.mContactResponseList = mContactResponseList;
    }

    @Override
    public int getItemCount() {
        if (mContactResponseList != null && mContactResponseList.size() > 0) {
            return mContactResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mContactResponseList != null && !mContactResponseList.isEmpty()) {
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
                ItemContactListBinding contactListBinding = ItemContactListBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new ContactViewHolder(contactListBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemContactEmptyViewBinding emptyViewBinding = ItemContactEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<Contact> contactList) {
        mContactResponseList.addAll(contactList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mContactResponseList.clear();
    }

    public void setListener(ContactAdapterListener listener) {
        this.mListener = listener;
    }

    public interface ContactAdapterListener {

        void onRetryClick();

        void onDialogClick(String name, String mobileno, Contact contact);
    }

    public class ContactViewHolder extends BaseViewHolder implements ContactItemViewModel.ContactItemViewModelListener {

        private ItemContactListBinding mBinding;

        private ContactItemViewModel mContactItemViewModel;

        public ContactViewHolder(ItemContactListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Contact contact = mContactResponseList.get(position);
            mContactItemViewModel = new ContactItemViewModel(contact, this);
            mBinding.setViewModel(mContactItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(String name, String mobileno, Contact contact) {

            mListener.onDialogClick(name, mobileno, contact);
          /*  if (blogUrl != null) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(blogUrl));
                    itemView.getContext().startActivity(intent);
                } catch (Exception e) {
                    AppLogger.d("url error");
                }
            }*/
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements ContactEmptyItemViewModel.ContactEmptyItemViewModelListener {

        private ItemContactEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemContactEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            ContactEmptyItemViewModel emptyItemViewModel = new ContactEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }
}