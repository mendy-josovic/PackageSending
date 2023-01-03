package com.example.packagesending


import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class FireBaseDBConnection {


    //interface for events if the operation was Success/fail.
    interface Action<T> {
        fun onSuccess(obj: String)
        fun onFailure(exception: Exception?)
        fun onProgress(status: String?, percent: Double)
    }

    interface NotifyDataChange<T> {
        fun OnDataChanged(obj: T)
        fun onFailure(exception: Exception?)
    }

    //list of Packages.
    var packages: MutableList<Package>

    // pointer to the root Packages in the fire base.
    var PackageMyRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Packages")

    init {
        packages = ArrayList<Package>()
    }

    //function that add package to the fire base.
    fun addPackageToFirebase(pack: Package, action: Action<Long?>) {
        pack.id = PackageMyRef.push().key.toString()
        PackageMyRef.child(pack.id).setValue(pack).addOnSuccessListener(OnSuccessListener {
            action.onSuccess(pack.id)
        })
            .addOnFailureListener(OnFailureListener{
                action.onFailure(it)
            })
    }


}