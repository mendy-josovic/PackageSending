package com.example.packagesending

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.FirebaseApp


class MainActivity : AppCompatActivity() {
    private lateinit var ownerNameEt: EditText
    private lateinit var ownerPhoneEt: EditText
    private lateinit var addressEt: EditText
    private lateinit var pacTypeSp: Spinner
    private lateinit var areaCode: Spinner
    private lateinit var latitudeSb: SeekBar
    private lateinit var longitudeSb: SeekBar
    private lateinit var latitudeTt: EditText
    private lateinit var longitudeTt: EditText
    private lateinit var weightEt: EditText
    private lateinit var seekBarWeight: SeekBar
    private lateinit var rbNo: RadioButton
    private lateinit var rbYes: RadioButton
    private lateinit var addButton: Button
    private lateinit var spinRTv: TextView

    private lateinit var dataBaseRef: FireBaseDBConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)

        dataBaseRef = FireBaseDBConnection()

        ownerNameEt = findViewById(R.id.ownerNameEt)
        addressEt = findViewById(R.id.ownerAddressEt)
        areaCode = findViewById(R.id.areaCode)
        ownerPhoneEt = findViewById(R.id.ownerPhoneEt)

        seekBarWeight = findViewById(R.id.seekBarWeight)
        weightEt = findViewById(R.id.weightEt)
        weightEt = findViewById(R.id.weightEt)
        pacTypeSp = findViewById(R.id.PacTypeSp)
        rbNo = findViewById(R.id.rbNo)
        rbYes = findViewById(R.id.rbYes)

        latitudeSb = findViewById(R.id.latitudeSb)
        longitudeSb = findViewById(R.id.longitudeSb)
        longitudeTt = findViewById(R.id.longitudeTt)
        latitudeTt = findViewById(R.id.latitudeTt)

        spinRTv = findViewById(R.id.spinRTv)
        addButton = findViewById(R.id.addButton)

        rbYes.isChecked = true

        //init for areas Code - spinner.
        var areasCode = ArrayList<String>()
        for (i in 0..9) {
            areasCode.add("05$i-")
        }

        // set Adapter of areas Code to the spinner.
        areaCode.adapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, areasCode
        )

        // set Adapter of Package Type to the spinner.
        pacTypeSp.adapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, PackageType.values()
        )

        // Item Selected for spinner.
        pacTypeSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinRTv.visibility = View.VISIBLE
                spinRTv.text = pacTypeSp.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                spinRTv.visibility = View.INVISIBLE
            }
        }

        rbNo.setOnClickListener(View.OnClickListener {
            rbYes.isChecked = rbNo.isChecked != true
        })

        rbYes.setOnClickListener(View.OnClickListener {

            rbNo.isChecked = rbYes.isChecked != true
        })

        // click event of seek bar: seekBarWeight.
        seekBarWeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            // Display the current progress of SeekBar
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                weightEt.setText(i.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // click event of seek bar: latitudeSb.
        latitudeSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            // Display the current progress of SeekBar
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                latitudeTt.setText(i.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // click event of seek bar: longitudeSb.
        longitudeSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            // Display the current progress of SeekBar
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                longitudeTt.setText(i.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // click event of button: addButton.
        addButton.setOnClickListener(View.OnClickListener {

            // add new package to the fire base.
            var pac: Package = Package(
                nameOfTheOwner = ownerNameEt.text.toString(),
                phoneNumberOfTheOwner = areaCode.selectedItem.toString() + ownerPhoneEt.text.toString(),
                addressOfOwner = addressEt.text.toString(),
                packageWeight = weightEt.text.toString().toDouble(),
                warehouseLocationLongitude = longitudeTt.text.toString().toDouble(),
                warehouseLocationLatitude = latitudeTt.text.toString().toDouble(),
                fragileContent = rbYes.isChecked,
                packageType = pacTypeSp.selectedItem as PackageType
            )


            dataBaseRef.addPackageToFirebase(pac, object : FireBaseDBConnection.Action<Long?> {
                override fun onSuccess(obj: String) {
                    Toast.makeText(baseContext, "insert id $obj", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(exception: Exception?) {
                    Toast.makeText(
                        baseContext,
                        """Error ${exception!!.message}""",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }

                override fun onProgress(status: String?, percent: Double) {}
            })

        })
    }
 }

        // init all Controls.


