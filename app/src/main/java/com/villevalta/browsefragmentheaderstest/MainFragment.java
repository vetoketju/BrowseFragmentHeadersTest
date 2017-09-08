/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.villevalta.browsefragmentheaderstest;

import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.PageRow;

public class MainFragment extends BrowseFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMainFragmentRegistry().registerFragment(PageRow.class, new MyFragmentFactory());
        setTitle(getString(R.string.browse_title));

        // Setting this to HEADERS_DISABLED will end up in a situation where the MyFragmentFactory.createFragment will not be called.
        setHeadersState(HEADERS_DISABLED);

        ArrayObjectAdapter rowAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        rowAdapter.add(new PageRow(new HeaderItem("First")));
        rowAdapter.add(new PageRow(new HeaderItem("Second")));
        rowAdapter.add(new PageRow(new HeaderItem("Third")));
        setAdapter(rowAdapter);


        // Here it seems to work, but results in having a UI bug:
        // Where the headers fragment should be, there is just empty room and the content is pushed to right
        // setHeadersState(HEADERS_DISABLED);

    }

    private class MyFragmentFactory extends BrowseFragment.FragmentFactory<ListRowFragment> {

        @Override
        public ListRowFragment createFragment(Object row) {
            return ListRowFragment.newInstance(((PageRow) row).getHeaderItem().getName());
        }
    }



}
