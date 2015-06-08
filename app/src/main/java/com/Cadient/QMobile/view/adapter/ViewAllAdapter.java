package com.Cadient.QMobile.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.model.remote.ActivityLogEntries;

/**
 * Created by ivan on 10/9/14.
 */
public class ViewAllAdapter extends AbstractAdapter<ActivityLogEntries> {

    private IDeleteItemListener<ViewAllAdapter, ActivityLogEntries> deleteItemListener;

    private ImageView ivDelete;

    public ViewAllAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        ivDelete = (ImageView) convertView.findViewById(R.id.img_delete);
        final View finalConvertView = convertView;
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteItemListener != null) {
                    deleteItemListener.deleteItem(ViewAllAdapter.this, getItem(position), finalConvertView);
                }
            }
        });
        ((TextView) convertView.findViewById(R.id.row1)).setText(getItem(position).getActivity().getName());
        StringBuilder sb = new StringBuilder();
        sb.append(getItem(position).getDuration() + " Minutes" + " | ");
        sb.append(getItem(position).getMiles() == null ? "" : getItem(position).getMiles() + " Mile" + " | ");
        sb.append(getItem(position).getSteps() == null ? "" : getItem(position).getSteps() + " Steps" + " | ");
        sb.append(getItem(position).getCalories() + " Calories");
        ((TextView) convertView.findViewById(R.id.row3)).setText(sb.toString());

        View viewSeparator = convertView.findViewById(R.id.separator);
        viewSeparator.setVisibility(getCount() - 1 == position ? View.INVISIBLE : View.VISIBLE);

        return convertView;
    }

    public void setDeleteItemListener(IDeleteItemListener<ViewAllAdapter, ActivityLogEntries> deleteItemListener) {
        this.deleteItemListener = deleteItemListener;
    }
}
