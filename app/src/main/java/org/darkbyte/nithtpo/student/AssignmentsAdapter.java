package org.darkbyte.nithtpo.student;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.darkbyte.nithtpo.R;

import java.util.List;

/**
 * Created by root on 11/3/17.
 */

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.MyViewHolder>{
    public List<AssignmentsModel> list;
    @Override
    public AssignmentsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance, parent, false);
        return new AssignmentsAdapter.MyViewHolder(itemView);}

    public AssignmentsAdapter(List<AssignmentsModel> List) {
        this.list = List;
    }

    @Override
    public void onBindViewHolder(final AssignmentsAdapter.MyViewHolder holder, int position) {
        final AssignmentsModel chaptermodel = list.get(position);
       // Toast.makeText(holder.chapter.getContext(),"edw",Toast.LENGTH_SHORT).show();
        Log.v("dwa","daw");
        holder.chapter.setText(chaptermodel.getAssignments());
        final Context context=holder.chapter.getContext();
        holder.chapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Assignments");
                alertDialog.setMessage("Click ok to download "+chaptermodel.getAssignments());
                alertDialog.setButton( Dialog.BUTTON_POSITIVE, "Download", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(context,Display.class);
                        Bundle  assign = new Bundle();
                        assign.putString("assign_url",chaptermodel.getAssign_url());
                        assign.putString("assign_desc",chaptermodel.getAssign_desc());
                        assign.putString("assign_name",chaptermodel.getAssignments());
                        i.putExtra("assign",assign);
                        context.startActivity(i);
                    }
                    });

                    alertDialog.setButton( Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener()    {
                        public void onClick(DialogInterface dialog, int which) {
                       alertDialog.cancel();
                        }
                        });

                        alertDialog.show();

            }
        });
    }
    public  void  refresh(List<AssignmentsModel> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button chapter;
        public MyViewHolder(View itemView) {
            super(itemView);
            chapter=(Button)itemView.findViewById(R.id.attendancebutton);
        }
    }
}