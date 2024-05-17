package edu.udb.retrofitappcrud

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlumnoAdapter(private val alumnos: List<Alumno>) : RecyclerView.Adapter<AlumnoAdapter.ViewHolder>() {

    private var onItemClick: OnItemClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tituloTextView: TextView = view.findViewById(R.id.tvTitulo)
        val descripcionTextView: TextView = view.findViewById(R.id.tvDescripcion)
        val tipoTextView: TextView = view.findViewById(R.id.tvTipo)
        val enlaceTextView: TextView = view.findViewById(R.id.tvEnlace)
        val imagenTextView: TextView = view.findViewById(R.id.tvImagen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.alumno_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val alumno = alumnos[position]
        holder.tituloTextView.text = alumno.titulo
        holder.descripcionTextView.text = alumno.descripcion
        holder.tipoTextView.text = alumno.tipo
        holder.enlaceTextView.text = alumno.enlace
        holder.imagenTextView.text = alumno.imagen

        // Agrega el escuchador de clics a la vista del elemento de la lista
        holder.itemView.setOnClickListener {
            onItemClick?.onItemClick(alumno)
        }
    }

    override fun getItemCount(): Int {
        return alumnos.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClick = listener
    }

    interface OnItemClickListener {
        fun onItemClick(alumno: Alumno)
    }
}
