package edu.umich.umcssa.umich_cssa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.umich.umcssa.umich_cssa.PageFragment.OnListFragmentInteractionListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link EntryContent} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPageRecyclerViewAdapter extends RecyclerView.Adapter<MyPageRecyclerViewAdapter.ViewHolder> {

    private final List<EntryContent.Item> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyPageRecyclerViewAdapter(List<EntryContent.Item> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitleView.setText(mValues.get(position).getTitle());
        holder.mAuthorView.setText(mValues.get(position).getAuthor());
        SimpleDateFormat format=new SimpleDateFormat("MMM dd HH:mm");
        Date date=new Date(mValues.get(position).getDate());
        holder.mDateView.setText(format.format(date));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(mValues.get(position).getPath());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mAuthorView;
        public final TextView mDateView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.title);
            mAuthorView = (TextView) view.findViewById(R.id.author);
            mDateView = (TextView)view.findViewById(R.id.date);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}
