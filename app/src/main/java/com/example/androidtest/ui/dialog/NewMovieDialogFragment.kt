package layout

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.androidtest.R
import com.example.androidtest.data.database.entities.MovieEntity
import com.example.androidtest.data.model.Movie
import com.example.androidtest.ui.MyMoviesFragment
import com.example.androidtest.ui.dialog.receiveMovie
import com.example.androidtest.viewmodel.MyMoviesViewModel
import java.lang.Exception

class NewMovieDialogFragment(val viewModel: MyMoviesViewModel) : DialogFragment() {
    lateinit var titleEditTExt: EditText
    lateinit var overviewEditText:EditText
    lateinit var imageButton:ImageButton
    lateinit var addBtn:Button
    lateinit var receiver:receiveMovie
    var byteArray: ByteArray?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_dialog_new_movie, container, false)
        titleEditTExt = rootView.findViewById(R.id.title_et)
        overviewEditText = rootView.findViewById(R.id.overview_et)
        imageButton = rootView.findViewById(R.id.imagebutton_new_movie)
        addBtn = rootView.findViewById(R.id.add_button)
        addBtn.setOnClickListener {
            val title= titleEditTExt.text.toString()
            val overview = overviewEditText.text.toString()
            if(title.length<2) {
                Toast.makeText(requireContext(), "Ingrese un titulo más largo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(overview.length<5) {
                Toast.makeText(requireContext(), "Ingrese un overview más largo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(byteArray==null){
                Toast.makeText(requireContext(), "Ingrese un overview más largo", Toast.LENGTH_SHORT).show()
            }
            else {
                saveMovie(title, overview, byteArray!!)
            }
        }
        imageButton.setOnClickListener {
            loadImage()
        }


        return rootView
    }
    fun saveMovie(title:String, overview:String, byteArray: ByteArray){
        viewModel.insertMovie(
            Movie(0,false, "", "",
            title,0.0,true, "","",title,0.0,byteArray)
        )
        viewModel.getMyMovies()
        this.dismiss()
    }
    fun loadImage(){
        var intent= Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.setType("image/")
        startActivityForResult(Intent.createChooser(intent,"Aplicaciones "), 78)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==RESULT_OK){
            data?.let {
                val uri = it.getData()
                imageButton.setImageURI(uri)
                uri?.let {
                    byteArray = readBytes(it)
                }
            }
        }

    }
    private fun readBytes(uri: Uri): ByteArray? {
        return try{
            requireContext().contentResolver.openInputStream(uri)?.buffered()?.use { it.readBytes() }
        }catch (ex:Exception){null}
    }

}
