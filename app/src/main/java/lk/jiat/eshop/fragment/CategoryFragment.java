package lk.jiat.eshop.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.List;

import lk.jiat.eshop.R;
import lk.jiat.eshop.adapter.CategoryAdapter;
import lk.jiat.eshop.databinding.FragmentCategoryBinding;
import lk.jiat.eshop.model.Category;


public class CategoryFragment extends Fragment {

   private FragmentCategoryBinding binding;

   private CategoryAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();

      }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerViewCategory.setLayoutManager(new GridLayoutManager(getContext(), 3));

        FirebaseFirestore db = FirebaseFirestore.getInstance();

//        Category c1 = new Category("cat1","toys","");
//        Category c2 = new Category("cat2","clothes","");
//        Category c3 = new Category("cat3","electronics","");
//        Category c4 = new Category("cat4","furniture","");
//        Category c5 = new Category("cat5","watches","");
//        Category c6 = new Category("cat6","books","");
//        Category c7 = new Category("cat7","watches","");
//        Category c8 = new Category("cat8","watches","");
//
//
//        List<Category> cats = List.of(c1,c2,c3,c4,c5,c6,c7,c8);
//
//
//        WriteBatch batch = db.batch();
//
//        for (Category category : cats){
//            DocumentReference ref = db.collection("categories").document();
//            batch.set(ref,category);
//
//        }
//
//        batch.commit();

//        db.collection("categories").add(c1);


        db.collection("categories").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        QuerySnapshot result = task.getResult();

                        List<Category> categories = task.getResult().toObjects(Category.class);
                        adapter = new CategoryAdapter(categories);
                        binding.recyclerViewCategory.setAdapter(adapter);




                    }
                });

    }
}