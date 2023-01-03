package com.example.packagesending


//Class that represents Package.

class Package(nameOfTheOwner : String, phoneNumberOfTheOwner : String, addressOfOwner : String, packageWeight : Double, warehouseLocationLongitude
: Double, warehouseLocationLatitude : Double, fragileContent : Boolean, packageType : PackageType
) {

    var id : String = ""
    var nameOfTheOwner : String = ""
    var phoneNumberOfTheOwner : String = ""
    var addressOfOwner : String = ""
    var packageWeight : Double = 0.0
    var warehouseLocationLongitude : Double = 0.0
    var warehouseLocationLatitude : Double = 0.0
    var fragileContent : Boolean = false
    var packageType : PackageType = PackageType.בינונית

    init {
        this.id = id
        this.nameOfTheOwner = nameOfTheOwner
        this.phoneNumberOfTheOwner = phoneNumberOfTheOwner
        this.addressOfOwner = addressOfOwner
        this.packageWeight = packageWeight
        this.warehouseLocationLongitude = warehouseLocationLongitude
        this.warehouseLocationLatitude = warehouseLocationLatitude
        this.fragileContent = fragileContent
        this.packageType = packageType
    }

    override fun toString(): String {
        return "Package(id=$id, nameOfTheOwner='$nameOfTheOwner', phoneNumberOfTheOwner='$phoneNumberOfTheOwner', addressOfOwner='$addressOfOwner', packageWeight=$packageWeight, warehouseLocationLongitude=$warehouseLocationLongitude, warehouseLocationLatitude=$warehouseLocationLatitude, fragileContent=$fragileContent, packageType=$packageType)"
    }
}