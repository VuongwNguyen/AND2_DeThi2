package com.example.and_dethi2.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_dethi2.Dao.ProductsDAO;
import com.example.and_dethi2.Model.Products;
import com.example.and_dethi2.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private List<Products> itemList;
    private Context context;

    private ProductsDAO productDAO;

    public ProductsAdapter(List<Products> itemList, Context context, ProductsDAO productDAO) {
        this.itemList = itemList;
        this.context = context;
        this.productDAO = productDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater  inflater = ((Activity)context).getLayoutInflater();
        return new ViewHolder(inflater.inflate(R.layout.item_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewTitle.setText(itemList.get(position).getName());
        holder.textViewDescription.setText(String.valueOf(itemList.get(position).getPrice() + "VNĐ" + " - " + " SL:" + itemList.get(position).getAmount()));
        if (itemList.get(position).getAvatar() != null) {
            int idImage = ((Activity) context).getResources().getIdentifier(String.valueOf(itemList.get(position).getAvatar()), "drawable", ((Activity) context).getPackageName());
            holder.ivAvatar.setImageResource(idImage);
        }
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh Báo");
                builder.setMessage("Xác Nhận Xoá Sản Phẩm '" + itemList.get(holder.getAdapterPosition()).getName() + "' ?");
                builder.setIcon(R.drawable.ic_warning_24);
                builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (productDAO.deleteProducts(itemList.get(holder.getAdapterPosition()).getID())) {
                            Toast.makeText(context, "Delete Completed !", Toast.LENGTH_SHORT).show();
                            itemList.clear();
                            itemList = productDAO.getListPD();
                            notifyItemRemoved(holder.getAdapterPosition());
                        } else {
                            Toast.makeText(context, "Error Deleting !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Products productUpadte = itemList.get(holder.getAdapterPosition());
                dialogUpdate(productUpadte);
            }
        });
    }
    private void dialogUpdate(Products productUpadate) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_product, null);

//        ánh xạ
        TextInputEditText edtNamePDe = view.findViewById(R.id.edtNamePD);
        TextInputEditText edtPricePDe = view.findViewById(R.id.edtPricePD);
        TextInputEditText edtAmountPDe = view.findViewById(R.id.edtAmountPD);
        TextInputEditText edtedtLinkPD = view.findViewById(R.id.edtLinkPD);
        ImageView ivHintPicturesUD = view.findViewById(R.id.ivHintPicturesUD);
        Button btnEdit = view.findViewById(R.id.btnUpdate);
        Button btnCancel = view.findViewById(R.id.btnCancel);
//        ImageButton ivFindPic = view.findViewById(R.id.findPictures);
// set dữ liệu lên lại ~~
        edtNamePDe.setText(productUpadate.getName());
        edtPricePDe.setText(String.valueOf(productUpadate.getPrice()));
        edtAmountPDe.setText(String.valueOf(productUpadate.getAmount()));
        edtedtLinkPD.setText(productUpadate.getAvatar());
        // lấy ảnh drawable
//        if (itemList.get(i).getAvatar() != null) {
//            int idImage = ((Activity) context).getResources().getIdentifier(String.valueOf(itemList.get(i).getAvatar()), "drawable", ((Activity) context).getPackageName());
//            ivHintPicturesUD.setImageResource(idImage);
//            //ảnh treen internet
//            if (itemList.get(i).getAvatar().startsWith("http://") || itemList.get(i).getAvatar().startsWith("https://")) {
//                Picasso.get().load(itemList.get(i).getAvatar()).into(ivHintPicturesUD);
//            } else {
//                ivHintPicturesUD.setImageResource(R.drawable.ic_all_inclusive);
//            }
//        }
        builder.setView(view);
        AlertDialog dialog = builder.create();
//        sự kiện bấm nút
//        ivFindPic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new FetchImage(ivHintPicturesUD, context, edtedtLinkPD.getText().toString()).start();
//            }
//        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Products product = new Products();
                product.setID(productUpadate.getID());
                product.setName(edtNamePDe.getText().toString());
                product.setPrice(Integer.parseInt(edtPricePDe.getText().toString()));
                product.setAmount(Integer.parseInt(edtAmountPDe.getText().toString()));
                product.setAvatar(edtedtLinkPD.getText().toString());
                if (productDAO.updateProducts(product)) {
                    Toast.makeText(context, "Update Completed !", Toast.LENGTH_SHORT).show();
                    itemList.clear();
                    itemList = productDAO.getListPD();
                    notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Error Updating!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewDescription;
        ImageView ivDelete, ivAvatar,ivEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.txtNameProduct);
            textViewDescription = itemView.findViewById(R.id.txtDescription);
            ivAvatar = itemView.findViewById(R.id.ivPictures);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }
}
