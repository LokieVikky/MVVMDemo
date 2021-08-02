package com.thesoftwarecompany.mvvmdemo.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thesoftwarecompany.mvvmdemo.Models.User;
import com.thesoftwarecompany.mvvmdemo.R;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    List<User> userList;

    public UsersAdapter() {
        this.userList = new ArrayList<>();
    }

    public void setData(List<User> userList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new UserDiffCallback(userList,this.userList));
        this.userList.clear();
        this.userList.addAll(userList);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UsersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.txtUser.setText(userList.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView txtUser;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUser = itemView.findViewById(R.id.txtUsername);
        }
    }

    public class UserDiffCallback extends DiffUtil.Callback{

        List<User> oldUsers;
        List<User> newUsers;

        public UserDiffCallback(List<User> newUsers, List<User> oldUsers) {
            this.newUsers = newUsers;
            this.oldUsers = oldUsers;
        }

        @Override
        public int getOldListSize() {
            return oldUsers.size();
        }

        @Override
        public int getNewListSize() {
            return newUsers.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldUsers.get(oldItemPosition).getUserID() == newUsers.get(newItemPosition).getUserID();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldUsers.get(oldItemPosition).equals(newUsers.get(newItemPosition));
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            //you can return particular field for changed item.
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }

}
