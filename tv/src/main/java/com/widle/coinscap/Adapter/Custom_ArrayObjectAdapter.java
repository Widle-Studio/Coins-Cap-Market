package com.widle.coinscap.Adapter;

import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.RowPresenter;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by imperial-web on 4/5/2018.
 */

public class Custom_ArrayObjectAdapter extends ObjectAdapter {

    private ArrayList<Object> mItems = new ArrayList<Object>();

    /**
     * Constructs an adapter with the given {@link PresenterSelector}.
     */
    public Custom_ArrayObjectAdapter(PresenterSelector presenterSelector) {
        super(presenterSelector);
    }

    /**
     * Constructs an adapter that uses the given {@link Presenter} for all items.
     */
    public Custom_ArrayObjectAdapter(Presenter presenter) {
        super(presenter);
    }

    /**
     * Constructs an adapter.
     */
    public Custom_ArrayObjectAdapter() {
        super();
    }

    @Override
    public int size() {
        return mItems.size();
    }

    @Override
    public Object get(int index) {
        return mItems.get(index);
    }

    /**
     * Returns the index for the first occurrence of item in the adapter, or -1 if
     * not found.
     *
     * @param item  The item to find in the list.
     * @return Index of the first occurrence of the item in the adapter, or -1
     *         if not found.
     */
    public int indexOf(Object item) {
        return mItems.indexOf(item);
    }

    /**
     * Notify that the content of a range of items changed. Note that this is
     * not same as items being added or removed.
     *
     * @param positionStart The position of first item that has changed.
     * @param itemCount The count of how many items have changed.
     */
    public void notifyArrayItemRangeChanged(int positionStart, int itemCount) {
        notifyItemRangeChanged(positionStart, itemCount);
    }

    /**
     * Adds an item to the end of the adapter.
     *
     * @param item The item to add to the end of the adapter.
     */
    public void add(Object item) {
        add(mItems.size(), item);
    }

    /**
     * Inserts an item into this adapter at the specified index.
     * If the index is > {@link #size} an exception will be thrown.
     *
     * @param index The index at which the item should be inserted.
     * @param item The item to insert into the adapter.
     */
    public void add(int index, Object item) {
        mItems.add(index, item);
        notifyItemRangeInserted(index, 1);
    }

    public void setmItems(int index, Object item) {
        mItems.add(index, item);
        notifyItemRangeInserted(index, 1);
    }
    /**
     * Adds the objects in the given collection to the adapter, starting at the
     * given index.  If the index is >= {@link #size} an exception will be thrown.
     *
     * @param index The index at which the items should be inserted.
     * @param items A {@link Collection} of items to insert.
     */
    public void addAll(int index, Collection items) {
        int itemsCount = items.size();
        if (itemsCount == 0) {
            return;
        }
        mItems.addAll(index, items);
        notifyItemRangeInserted(index, itemsCount);
    }

    /**
     * Removes the first occurrence of the given item from the adapter.
     *
     * @param item The item to remove from the adapter.
     * @return True if the item was found and thus removed from the adapter.
     */
    public boolean remove(Object item) {
        int index = mItems.indexOf(item);
        if (index >= 0) {
            mItems.remove(index);
            notifyItemRangeRemoved(index, 1);
        }
        return index >= 0;
    }

    /**
     * Replaces item at position with a new item and calls notifyItemRangeChanged()
     * at the given position.  Note that this method does not compare new item to
     * existing item.
     * @param position  The index of item to replace.
     * @param item      The new item to be placed at given position.
     */
    public void replace(int position, Object item) {
        mItems.set(position, item);
        notifyItemRangeChanged(position, 1);
    }

    /**
     * Removes a range of items from the adapter. The range is specified by giving
     * the starting position and the number of elements to remove.
     *
     * @param position The index of the first item to remove.
     * @param count The number of items to remove.
     * @return The number of items removed.
     */
    public int removeItems(int position, int count) {
        int itemsToRemove = Math.min(count, mItems.size() - position);
        if (itemsToRemove <= 0) {
            return 0;
        }

        for (int i = 0; i < itemsToRemove; i++) {
            mItems.remove(position);
        }
        notifyItemRangeRemoved(position, itemsToRemove);
        return itemsToRemove;
    }

    /**
     * Removes all items from this adapter, leaving it empty.
     */
    public void clear() {
        int itemCount = mItems.size();
        if (itemCount == 0) {
            return;
        }
        mItems.clear();
        notifyItemRangeRemoved(0, itemCount);
    }

    /**
     * Gets a read-only view of the list of object of this ArrayObjectAdapter.
     */
    public <E> List<E> unmodifiableList() {
        return Collections.unmodifiableList((List<E>) mItems);
    }

    @Override
    public boolean isImmediateNotifySupported() {
        return true;
    }
}
