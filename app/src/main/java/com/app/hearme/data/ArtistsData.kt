package com.app.hearme.data

import com.app.hearme.model.Artist

class ArtistsData {
    companion object {
        fun dataArtist(): ArrayList<Artist> {
            val dataArtist = ArrayList<Artist>()
            dataArtist.apply {
                add(
                    Artist(
                        "ar001",
                        "Ariana Grande",
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fartists%2FAriana_Grande.png?alt=media&token=51f77e64-3fa9-4409-b3bb-dbb5afb3a1b3&_gl=1*13i06uz*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4MTg4Mi42LjEuMTY5NTg4MzA2MC4xNS4wLjA.",
                        0,
                        true
                    )
                )
                add(
                    Artist(
                        "ar002",
                        "The Weeknd",
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fartists%2FThe_Weeknd.png?alt=media&token=48f8ca85-667e-4c4c-80ef-05eb3b584e3d&_gl=1*16ban4k*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4MTg4Mi42LjEuMTY5NTg4MzA4Mi42MC4wLjA.",
                        0,
                        true
                    )
                )
                add(
                    Artist(
                        "ar003",
                        "Acidrap",
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fartists%2FAcidrap.png?alt=media&token=26e35e3e-d65f-4c62-b66e-ab960fe3242a&_gl=1*i1t3m4*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4MTg4Mi42LjEuMTY5NTg4MzA5OS40My4wLjA.",
                        0,
                        true
                    )
                )
                add(
                    Artist(
                        "ar004",
                        "Ryan Jones",
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fartists%2FRyan_Jones.png?alt=media&token=30821e3f-4d83-4c95-8e37-c7b4e6d9a5c6&_gl=1*1y0plpm*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4MTg4Mi42LjEuMTY5NTg4MzExOC4yNC4wLjA.",
                        0,
                        true,
                    )
                )
                add(
                    Artist(
                        "ar005",
                        "Jamie Gray",
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fartists%2FJaime_Rray.png?alt=media&token=f264c90e-1dd0-4cbd-b2b5-eb29e7cc7c17&_gl=1*1x2q8mz*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4MTg4Mi42LjEuMTY5NTg4MzE5Ny4zOS4wLjA.",
                        0,
                        true,
                    )
                )
                add(
                    Artist(
                        "ar006",
                        "Troye Sivan",
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fartists%2FTroye_Sivan.png?alt=media&token=6bf9db5a-0dac-4068-9fe8-9eeb0db76457&_gl=1*l7rlxp*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4MTg4Mi42LjEuMTY5NTg4MzI3NC41NS4wLjA.",
                        0,
                        true,
                    )
                )
                add(
                    Artist(
                        "ar007",
                        "Apple Talk",
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fartists%2FApple_Talk.png?alt=media&token=e858d8b3-be16-4c86-9d5b-26f92ab04a3b&_gl=1*1xi1e1r*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4MTg4Mi42LjEuMTY5NTg4MzI4OS40MC4wLjA.",
                        0,
                        false,
                        "Gravis est aegrum curare, quam aegrum sequi, sed id futurum tempore, cum multum laboris et doloris est. Magnum hoc spectaculum et unum cacumen ostendit. Per id videre possumus eum esse doctorem disertum."
                    )
                )
                add(
                    Artist(
                        "ar008",
                        "Dr. Death",
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fartists%2FDr_Death.png?alt=media&token=6d4c9d1e-01ea-446b-a5c7-5222029c8843&_gl=1*hoi3dr*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4MTg4Mi42LjEuMTY5NTg4MzMwMS4yOC4wLjA.",
                        0,
                        false,
                        "Gravis est aegrum curare, quam aegrum sequi, sed id futurum tempore, cum multum laboris et doloris est. Magnum hoc spectaculum et unum cacumen ostendit. Per id videre possumus eum esse doctorem disertum."
                    )
                )
                add(
                    Artist(
                        "ar009",
                        "The Jordan Harbinger Show",
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fartists%2FJordan_Show.png?alt=media&token=1c2888ea-31a2-436c-809d-5ea17bc45ebc&_gl=1*1ilq7og*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4MTg4Mi42LjEuMTY5NTg4MzMxOC4xMS4wLjA.",
                        0,
                        false,
                        "Gravis est aegrum curare, quam aegrum sequi, sed id futurum tempore, cum multum laboris et doloris est. Magnum hoc spectaculum et unum cacumen ostendit. Per id videre possumus eum esse doctorem disertum."
                    )
                )
                add(Artist("ar010",
                    "Katy Perry",
                    "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fartists%2FKatyperry.png?alt=media&token=a31da179-d61a-4ca0-8bea-0093b5420e24&_gl=1*17843g9*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4MTg4Mi42LjEuMTY5NTg4MzMyOS42MC4wLjA.",
                    0,
                    true))
            }
            return dataArtist
        }
    }
}