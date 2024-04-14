package br.unip.yasaw.presenter.adapters.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.unip.yasaw.data.models.EquipmentModel
import br.unip.yasaw.databinding.ItemEquipmentBinding

class EquipmentsAdapter(
    private val products: MutableList<EquipmentModel>
) : RecyclerView.Adapter<EquipmentsAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(
        private val binding: ItemEquipmentBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(equipmentModel: EquipmentModel) {
            binding.apply {
                productName.text = equipmentModel.name
                productDescription.text = equipmentModel.description
                productImage.setImageResource(equipmentModel.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ItemEquipmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

}