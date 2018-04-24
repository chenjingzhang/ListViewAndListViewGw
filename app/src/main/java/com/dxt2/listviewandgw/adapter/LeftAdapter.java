package com.dxt2.listviewandgw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dxt2.listviewandgw.R;
import com.dxt2.listviewandgw.bean.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class LeftAdapter extends BaseAdapter{
    private List<MenuTagSection>  list =null;
    private Context context;

    public LeftAdapter(Context context,List<Product> list) {
        this.list = getTagSections(list);
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public MenuTagSection getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        final MenuTagSection mContent = list.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.product_menu_item, null);
            viewHolder.tvTag = (TextView) convertView.findViewById(R.id.menu_tag_tv);
            viewHolder.tvSection = (TextView) convertView.findViewById(R.id.section_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvSection.setText(String.valueOf(mContent.getMenuSection()));

        viewHolder.tvTag.setText(mContent.getMenuTag());


        return convertView;
    }

    final static class ViewHolder {
        TextView tvTag;
        TextView tvSection;
    }

    public final static class MenuTagSection {
        String menuTag;
        int menuSection;

        public MenuTagSection(String menuTag, int menuSection) {
            this.menuTag = menuTag;
            this.menuSection = menuSection;
        }

        public String getMenuTag() {
            return menuTag;
        }

        public int getMenuSection() {
            return menuSection;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MenuTagSection that = (MenuTagSection) o;

            if (menuSection != that.menuSection) return false;
            return menuTag != null ? menuTag.equals(that.menuTag) : that.menuTag == null;

        }

        @Override
        public int hashCode() {
            int result = menuTag != null ? menuTag.hashCode() : 0;
            result = 31 * result + menuSection;
            return result;
        }
    }

    /**
     * 此处遍历商品去掉相同的商品类型
     * 显示商品类型
     * 左边菜单-----类型
     * @param list
     * @return
     */
    private List<MenuTagSection> getTagSections(List<Product> list) {
        List<MenuTagSection> mtsList = new ArrayList<MenuTagSection>();
        for (int i = 0; i < list.size(); i++) {
            if (!mtsList.contains(new MenuTagSection(list.get(i).getTitle(), list.get(i).getSeleteId()))) {
                mtsList.add(new MenuTagSection(list.get(i).getTitle(), list.get(i).getSeleteId()));
            }
        }
        //去除重复
        Set<MenuTagSection> ts = new HashSet<MenuTagSection>();
        List<MenuTagSection> newMtsList = new ArrayList<MenuTagSection>();
        for (Iterator<MenuTagSection> iter = mtsList.iterator(); iter.hasNext(); ) {
            MenuTagSection ele = iter.next();
            if (ts.add(ele)) {
                newMtsList.add(ele);
            }
        }
        return newMtsList;
    }



}
