package lk.jiat.eshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import lk.jiat.eshop.R;
import lk.jiat.eshop.model.Category;

public class CategoryAdapter extends  RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private List<Category> categories;
    private OnCategoryClickListener listener;


    public CategoryAdapter(List<Category> categories , OnCategoryClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }


    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        Category category = categories.get(position);
        holder.categoryName.setText(category.getName());
        Glide.with(holder.itemView.getContext())
                .load(category.getImageUrl())
                .centerCrop()
                .into(holder.imageView );

        holder.itemView.setOnClickListener(View -> {
            if (listener != null) {
                listener.onCategoryClick(category);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
ImageView imageView;

TextView categoryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.category_image);
            categoryName = itemView.findViewById(R.id.category_name);

        }

    }
    public  interface  OnCategoryClickListener{
        void onCategoryClick(Category category);

    }
}
