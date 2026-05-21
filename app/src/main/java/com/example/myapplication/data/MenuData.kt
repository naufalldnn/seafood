package com.example.myapplication.data

import com.example.myapplication.R

object MenuData {
    val menuList = listOf(
        MenuItem(
            "1",
            "Lobster Thermidor",
            350000,
            "Lobster segar yang dipanggang dengan saus krim keju, mustard, dan brandy, disajikan dengan parmesan crust.",
            R.drawable.seafood.toString()
        ),
        MenuItem(
            "2",
            "Grilled King Prawns",
            150000,
            "Udang galah besar yang dipanggang dengan mentega bawang putih dan bumbu rempah pilihan.",
            R.drawable.seafood.toString()
        ),
        MenuItem(
            "3",
            "Fish and Chips",
            85000,
            "Fillet ikan dory segar dengan balutan tepung renyah, disajikan dengan kentang goreng dan saus tartar.",
            R.drawable.seafood.toString()
        ),
        MenuItem(
            "4",
            "Cumi Goreng Tepung",
            75000,
            "Cumi-cumi segar yang dipotong melingkar, digoreng dengan tepung bumbu yang gurih dan renyah.",
            R.drawable.seafood.toString()
        ),
        MenuItem(
            "5",
            "Kepiting Saus Padang",
            250000,
            "Kepiting bakau segar dimasak dengan saus Padang yang pedas, manis, dan gurih yang meresap.",
            R.drawable.seafood.toString()
        ),
        MenuItem(
            "6",
            "Kerang Hijau Saus Tiram",
            65000,
            "Kerang hijau pilihan yang dimasak dengan saus tiram kental dan irisan jahe segar.",
            R.drawable.seafood.toString()
        ),
        MenuItem(
            "7",
            "Salmon Steak",
            185000,
            "Steak salmon Norwegia yang dipanggang sempurna, disajikan dengan saus lemon butter dan asparagus.",
            R.drawable.seafood.toString()
        ),
        MenuItem(
            "8",
            "Sup Ikan Tom Yum",
            90000,
            "Sup seafood ala Thailand yang pedas dan asam dengan campuran udang, cumi, dan potongan ikan.",
            R.drawable.seafood.toString()
        ),
        MenuItem(
            "9",
            "Ikan Bakar Jimbaran",
            120000,
            "Ikan kakap merah yang dibakar dengan bumbu khas Jimbaran Bali, disajikan dengan sambal matah.",
            R.drawable.seafood.toString()
        ),
        MenuItem(
            "10",
            "Sate Gurita",
            80000,
            "Potongan gurita yang empuk dibakar dengan bumbu kacang atau kecap pedas.",
            R.drawable.seafood.toString()
        )
    )

    fun getMenuById(id: String): MenuItem? {
        return menuList.find { it.id == id }
    }
}
