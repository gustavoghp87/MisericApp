package com.paquete.misericapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mContext;
    private List<UploadFoto> mUploads;

    public ImageAdapter(Context context, List<UploadFoto> uploadFotos) {
        mContext = context;
        mUploads = uploadFotos;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item_pizarra, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        UploadFoto uploadCurrent = mUploads.get(position);
        holder.textViewNamePizarraEmail.setText(uploadCurrent.getEmail());
        holder.textViewNamePizarra.setText(uploadCurrent.getName());
        holder.textViewFechaPizarra.setText(uploadCurrent.getFechayhora());

        Picasso.get()
                .load(uploadCurrent.getmImagenUrl())
//                .resize(2000, 2000)
//                .onlyScaleDown()
//                .fit()
                .into(holder.imageViewPizarra);
        //resize a secas comprime/estira Vs. resize con centerCrop recorta lo que exceda
        //                               Vs. resize con centerInside estira
        //                               Vs. resize con onlyScaleDown
        //fit
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNamePizarraEmail;
        public TextView textViewNamePizarra;
        public TextView textViewFechaPizarra;
        public ImageView imageViewPizarra;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNamePizarraEmail = itemView.findViewById(R.id.text_view_name_pizarra_email);
            textViewNamePizarra = itemView.findViewById(R.id.text_view_name_pizarra);
            textViewFechaPizarra = itemView.findViewById(R.id.text_view_pizarra_fecha);
            imageViewPizarra = itemView.findViewById(R.id.image_view_pizarra);
        }
    }
}
