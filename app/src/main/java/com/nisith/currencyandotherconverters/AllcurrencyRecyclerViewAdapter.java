package com.nisith.currencyandotherconverters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllcurrencyRecyclerViewAdapter extends RecyclerView.Adapter<AllcurrencyRecyclerViewAdapter.MyViewHolder> implements Filterable {

    private ArrayList<CountryAndCurrency> allCountryAndCurrenciesArrayList;
    private ArrayList<CountryAndCurrency> anotherAllCountryAndCurrenciesArrayList;//this arraylist is for search the rows in recycler View
    private OnCardViewClickListener onCardViewClickListener;

    interface OnCardViewClickListener{
        void onCardViewClick(int position);
    }

     AllcurrencyRecyclerViewAdapter(ArrayList<CountryAndCurrency> allCountryAndCurrenciesArrayList, OnCardViewClickListener onCardViewClickListener){
        this.allCountryAndCurrenciesArrayList = allCountryAndCurrenciesArrayList;
        this.anotherAllCountryAndCurrenciesArrayList = new ArrayList<>(allCountryAndCurrenciesArrayList);
        this.onCardViewClickListener = onCardViewClickListener;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_row_apperance_for_currency_name_country_name,viewGroup,false);
        return new MyViewHolder(view,onCardViewClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        myViewHolder.countryName.setText(allCountryAndCurrenciesArrayList.get(position).getCountryName());
        myViewHolder.currencyName.setText(allCountryAndCurrenciesArrayList.get(position).getCurrencyName());
        String countryName = allCountryAndCurrenciesArrayList.get(position).getCountryName();
        setImageThumbnailInRow(""+countryName.charAt(0),myViewHolder.imageThumbnailTextView);

    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (allCountryAndCurrenciesArrayList !=null){
            size = allCountryAndCurrenciesArrayList.size();
        }
        return size;

    }

    @Override
    public Filter getFilter() {
        return new MyFilter();
    }

   private class MyFilter extends Filter{


       @Override
       protected FilterResults performFiltering(CharSequence constraint) {
           //this method runs in background Thread
           ArrayList<CountryAndCurrency> myArrayList = new ArrayList<>();
           if (constraint.toString().length()==0){
               myArrayList.addAll(anotherAllCountryAndCurrenciesArrayList);
           }else {
               String searchText = constraint.toString().toLowerCase().trim();
               for (CountryAndCurrency item : anotherAllCountryAndCurrenciesArrayList){
                   if (item.getCountryName().toLowerCase().contains(searchText)){
                       myArrayList.add(item);
                   }
               }
           }
           FilterResults filterResults = new FilterResults();
           filterResults.values = myArrayList;
           return filterResults;
       }

       @Override
       protected void publishResults(CharSequence constraint, FilterResults results) {
           //this method run on UI Thread
           allCountryAndCurrenciesArrayList.clear();
           allCountryAndCurrenciesArrayList.addAll((ArrayList)results.values);
           notifyDataSetChanged();
       }
   }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView countryName,currencyName,imageThumbnailTextView;

        public MyViewHolder(@NonNull View itemView, final OnCardViewClickListener onCardViewClickListener) {
            super(itemView);
            countryName = itemView.findViewById(R.id.country_name_text_view);
            currencyName = itemView.findViewById(R.id.currency_name_text_view);
            imageThumbnailTextView = itemView.findViewById(R.id.image_thumbnail_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCardViewClickListener.onCardViewClick(getAdapterPosition());
                }
            });
        }
    }


    private void setImageThumbnailInRow(String firstLetter,TextView imageThumbnailTextView){
         if (firstLetter.equalsIgnoreCase("A") || firstLetter.equalsIgnoreCase("F") || firstLetter.equalsIgnoreCase("K")
          || firstLetter.equalsIgnoreCase("Q") || firstLetter.equalsIgnoreCase("W")){

             imageThumbnailTextView.setBackgroundResource(R.drawable.ic_disk3);
             imageThumbnailTextView.setText(firstLetter);

         }else if (firstLetter.equalsIgnoreCase("B") || firstLetter.equalsIgnoreCase("G") || firstLetter.equalsIgnoreCase("L")
          || firstLetter.equalsIgnoreCase("R") || firstLetter.equalsIgnoreCase("X")){
             imageThumbnailTextView.setBackgroundResource(R.drawable.ic_disk1);
             imageThumbnailTextView.setText(firstLetter);

         }else if(firstLetter.equalsIgnoreCase("C") || firstLetter.equalsIgnoreCase("H") || firstLetter.equalsIgnoreCase("M")
                 || firstLetter.equalsIgnoreCase("S") || firstLetter.equalsIgnoreCase("Y")){
             imageThumbnailTextView.setBackgroundResource(R.drawable.ic_disk2);
             imageThumbnailTextView.setText(firstLetter);

         }else if (firstLetter.equalsIgnoreCase("D") || firstLetter.equalsIgnoreCase("I") || firstLetter.equalsIgnoreCase("N")
                 || firstLetter.equalsIgnoreCase("T") || firstLetter.equalsIgnoreCase("Z")){
             imageThumbnailTextView.setBackgroundResource(R.drawable.ic_disk4);
             imageThumbnailTextView.setText(firstLetter);

         }else if (firstLetter.equalsIgnoreCase("E") || firstLetter.equalsIgnoreCase("J") || firstLetter.equalsIgnoreCase("O")
                 || firstLetter.equalsIgnoreCase("U") ){
             imageThumbnailTextView.setBackgroundResource(R.drawable.ic_disk5);
             imageThumbnailTextView.setText(firstLetter);

         }else if (firstLetter.equalsIgnoreCase("P") || firstLetter.equalsIgnoreCase("V")){
             imageThumbnailTextView.setBackgroundResource(R.drawable.ic_disk6);
             imageThumbnailTextView.setText(firstLetter);
        }
    }




}
