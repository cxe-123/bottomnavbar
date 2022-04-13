package com.example.worklah.model

//when i get data, i set the datatype to User and cast it
//if cannot cast, get it one by one, pass manually to object, and use that object instead
data class User (var fName:String="",
                 var lName:String="",
                 var gender:String="",
                 var ic:String="",
                 var dob:String="",
                 var email:String="",
                 var phone:String="",
                 var address:String="",
                 var bankAcc:String="",
                 var password:String="",
                 var passwordCfm:String="",

)