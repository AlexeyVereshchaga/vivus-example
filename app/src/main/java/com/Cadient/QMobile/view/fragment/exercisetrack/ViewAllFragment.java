package com.Cadient.QMobile.view.fragment.exercisetrack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ListView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.handler.BaseHandler;
import com.Cadient.QMobile.handler.BaseRemoteListener;
import com.Cadient.QMobile.model.remote.ActivitiesList;
import com.Cadient.QMobile.model.remote.ActivityLogEntries;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.view.activity.MainActivity;
import com.Cadient.QMobile.view.adapter.IDeleteItemListener;
import com.Cadient.QMobile.view.adapter.ViewAllAdapter;
import com.Cadient.QMobile.view.dialog.ConfirmDialog;
import com.Cadient.QMobile.view.fragment.AbstractFragment;
import com.Cadient.QMobile.view.fragment.IDataChangeListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by ivan on 10/9/14.
 */
public class ViewAllFragment extends AbstractFragment<MainActivity> {

    private IDataChangeListener dataChangeListener;

    private ListView list;
    private ViewAllAdapter allAdapter;
    private Long date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date = getArguments().getLong(HelpUtils.EXERCISE_DATE_BUNDLE_KEY);
        }
    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragm_view_all;
    }

    @Override
    protected void assignViews(View view) {
        list = (ListView) view.findViewById(R.id.list);
    }

    @Override
    protected void initView(View view) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(date);
            getBaseActivity().setCalendar(calendar);
        }
        allAdapter = new ViewAllAdapter(getActivity(), R.layout.universal_list_item);
        allAdapter.setDeleteItemListener(new DeleteItemListener());
        list.setAdapter(allAdapter);
        getBaseActivity().getDataManager().getActivitiesList(
                getBaseActivity().getCalendar().getTime(), new BaseHandler<ActivitiesList>(
                        new ActivitiesRemoteListener(getActivity()), ActivitiesList.class)
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        SimpleDateFormat format = new SimpleDateFormat(HelpUtils.DATE_FORMAT_HEADER);
        getBaseActivity().getHeaderController().setTitle(format.format(getBaseActivity().getCalendar().getTime()));
        if (getHeaderController() != null) {
            getHeaderController().setLeftButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getTransitManager().back();
                }
            }, true, R.drawable.arrow_back);
        }
        getBaseActivity().getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void onPause() {
        super.onPause();
        getBaseActivity().getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }


    public void setDataChangeListener(IDataChangeListener dataChangeListener) {
        this.dataChangeListener = dataChangeListener;
    }

    private class ActivitiesRemoteListener extends BaseRemoteListener<ActivitiesList> {
        protected ActivitiesRemoteListener(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public void onSuccess(ActivitiesList result) {
            List<ActivityLogEntries> entriesList = result.getActivityLogEntriesList();
            Collections.reverse(entriesList);
            allAdapter.addAll(entriesList);
        }
    }

    private class DeleteItemListener implements IDeleteItemListener<ViewAllAdapter, ActivityLogEntries> {

        @Override
        public void deleteItem(final ViewAllAdapter adapter, final ActivityLogEntries entries, View view) {

            ConfirmDialog.showDlg(getActivity(), getActivity().getString(R.string.confirm_dialog_activity_txt)).setOnDialogCloseListener(new ConfirmDialog.OnDialogCloseListener() {
                @Override
                public void OnDialogClose(ConfirmDialog.ConfirmDialogResult result) {
                    if (result == ConfirmDialog.ConfirmDialogResult.DELETE) {
                        adapter.remove(entries);
                        getBaseActivity().getDataManager().deleteActivityLogEntries(entries,
                                new BaseHandler<ActivityLogEntries>(new DeleteActivityEntriesRemoteListener(getActivity()), ActivityLogEntries.class));
                    }
                }
            });
        }
    }

    private class DeleteActivityEntriesRemoteListener extends BaseRemoteListener<ActivityLogEntries> {

        protected DeleteActivityEntriesRemoteListener(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public void onSuccess(ActivityLogEntries result) {
            if (dataChangeListener != null) {
                dataChangeListener.onChanged(getBaseActivity().getCalendar().getTime());
            }
        }
    }

}
