package com.app.hearme.data

import com.app.hearme.model.*
import java.util.*

class MusicsData {
    companion object {
        fun dataMusic(): ArrayList<Music> {
            val dataMusic = ArrayList<Music>()
            val calToday = Calendar.getInstance()
            val calYesterday = Calendar.getInstance()
            calYesterday.add(Calendar.DATE, -1)

            dataMusic.apply {
                add(
                    Music(
                        "ms001",
                        "Shades of Love",
                        231,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fmusics%2Fshades_of_love.png?alt=media&token=73c0c3e8-214b-422c-a97f-7cddd372473b&_gl=1*1p8k3a8*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4NjQxNS43LjEuMTY5NTg4ODA5OS42MC4wLjA.",
                        Time(0, 4, 30),
                        calToday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca001" },
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        true,
//                        "http://api.mp3.zing.vn/api/streaming/song/Z66IABZF/128"
                    )
                )
                add(
                    Music(
                        "ms002",
                        "Without You",
                        55,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fmusics%2Fwithout_you.png?alt=media&token=e78ac2d2-7e0e-4373-84f1-5f2d3b7ef445&_gl=1*1gfxh8m*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4NjQxNS43LjEuMTY5NTg4ODExOS40MC4wLjA.",
                        Time(0, 3, 30),
                        Date("02/5/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca001" },
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        false
                    )
                )
                add(
                    Music(
                        "ms003",
                        "Save Your Tears",
                        4,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fmusics%2Fsave_of_tear.png?alt=media&token=9d820dc3-834b-4e68-9416-2b5004d69c1f&_gl=1*o4326x*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4NjQxNS43LjEuMTY5NTg4ODE2MC42MC4wLjA.",
                        Time(0, 5, 30),
                        calYesterday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca005" },
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        true
                    )
                )
                add(
                    Music(
                        "ms004",
                        "Kiss Me More",
                        45,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fmusics%2Fkiss_me_more.png?alt=media&token=d976b37a-f9a8-4477-bcb2-f4f2d655ce38&_gl=1*wesnr6*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4NjQxNS43LjEuMTY5NTg4ODIwOC4xMi4wLjA.",
                        Time(0, 3, 20),
                        Date("01/08/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca005" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        false
                    )
                )
                add(
                    Music(
                        "ms005",
                        "Drivers License",
                        200,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fmusics%2Fdriver.png?alt=media&token=b1ca7849-d970-4c9e-98c2-28500cca6e76&_gl=1*19b29n9*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4NjQxNS43LjEuMTY5NTg4ODI0OC41NS4wLjA.",
                        Time(0, 2, 15),
                        calYesterday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca003" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        false
                    )
                )
                add(
                    Music(
                        "ms006",
                        "Forever After All",
                        600,
                        "https://s3-alpha-sig.figma.com/img/8328/f145/8bf703f1334a093e8168e707aa49ae97?Expires=1696809600&Signature=K5eWbNPypaiUcAPxqvVAn~TjHeRHkWvSRwp~gQQ3Dz6ns9eYauW2rb0cRvTvegDLQ3ur69~X0oU-PisjLfmIf72tM52iBT42bNAsaU9FZxZ5WdHTehOOOzPTGZC88mRbR96jZqhH27M0xDnvY45nDPU9ToFCoNCrcgN6IqJCsNNn0l6zihXejIQ-I7bDQCpOBIYoZSlyt0JW5HIK6b5FrPRZh-VbAK7idhi4L8nQXXbwrPWWScwkLIU0qg6UkUWuFFLUpe~n6SN6yIphsr8gzcICdu0KtJv1OYccfmbNSRJN-1zfJ2nrFy-OEqNaP6oDqNtJOxEe0zNgzXN1gNBl1w__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 4, 40),
                        Date("11/05/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca003" },
                        ArtistsData.dataArtist().first { it.artistId == "ar003" },
                        true
                    )
                )
                add(
                    Music(
                        "ms007",
                        "Break My Soul",
                        48,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fmusics%2Fbreak_my_soul.png?alt=media&token=ffd3ef67-30ee-4bab-9b34-8102e11fde15&_gl=1*voe9qt*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4NjQxNS43LjEuMTY5NTg4ODMxNC42MC4wLjA.",
                        Time(0, 5, 10),
                        Date("12/07/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca004" },
                        ArtistsData.dataArtist().first { it.artistId == "ar003" },
                        false
                    )
                )
                add(
                    Music(
                        "ms008",
                        "The Hills",
                        120,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fmusics%2Fthe_hill.png?alt=media&token=19bb8143-f14f-4d42-b0ef-e5f0f99962ba&_gl=1*e07k94*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4NjQxNS43LjEuMTY5NTg4ODQyNS41NC4wLjA.",
                        Time(0, 5, 20),
                        calYesterday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca004" },
                        ArtistsData.dataArtist().first { it.artistId == "ar005" },
                        true
                    )
                )
                add(
                    Music(
                        "ms009",
                        "593: Dallas Taylor | The Psychology of Sound Design",
                        200,
                        "https://s3-alpha-sig.figma.com/img/9b23/9488/35a4ee044e985f4fc0ad99aa61f67225?Expires=1696809600&Signature=NUACqhFHW3sXoIj6EsvfeGfzAJBfndybsqG2CkGRYKOwsts4yFpCw~kFM7ESer2JwYLN~I9U0KmyYreSk8Z30ITpLqbeu1D9EHGsYWRwzd~swAEFUFny0EIVAy5YYNi3s7i-SmDroNctg-RleXArNnKImTbUXyp1hlS7b-NU0FBEP5poX12TaNZYJ2mzywH70Y1W7byOkDLPeA52lP5sigfrFqceXB-ZV~7XzxJsP0yxtkXD~2K6RIAWuOPiJ2SXpXG18llZv6h2PZ0cOH-0GJSaZNgq9YjLzkT17qDnDreN6hdh2l83Y53hdCawegQDrCGsBLpCxXIfcDvucTLHFw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 48, 35),
                        calYesterday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar008" },
                        false,
                        "https://cdn.pixabay.com/audio/2023/07/03/audio_80cd47077b.mp3",
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
                add(
                    Music(
                        "ms010",
                        "837: Amy Webb | Changing Lives with Synthetic Biology  ",
                        500,
                        "https://s3-alpha-sig.figma.com/img/e11b/0138/2a27cf992de63e5ce47a0a8da996c096?Expires=1696809600&Signature=L-sQCu5lrY4GKLNhfUWNOH2GVOtOUak49SPQlGOiMV5sQcmQ61GAvuVWiOoT4nuonsBfgwQM71byNVjLlGqbmdtWwezbCMVrsp8bYBPOdjqrZkv2uscPAJopbX2jJhxkN6OZALgOnCdLhnHptE~~jMfdXHp9Q9JDFrbN5IkAad--2oCvTqFB25ZaAbteRoAVvgYEZnJ-Bnp64DayddTVukzYetQz0~E2MAU38AVwWTDMC0~-vYTsXCIGiBhvtf53iZQOOyfQtnNM4ls0W8ie5R-P3pbw5Ir6FrI6LqdQUmItdF8q~RELN4nwUVA0bNRoegxqQ2P-cr7Cw~7BIuMiNg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 34, 54),
                        calToday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar007" },
                        false,
                        "https://cdn.pixabay.com/audio/2023/07/03/audio_80cd47077b.mp3",
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
                add(
                    Music(
                        "ms011",
                        "Disater",
                        1050,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fmusics%2Fdisaster.png?alt=media&token=706b8d54-9b5a-4bf8-a2cd-6441fcce342a&_gl=1*187hfe6*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4NjQxNS43LjEuMTY5NTg4ODUxNC42MC4wLjA.",
                        Time(0, 5, 23),
                        Date("25/03/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca006" },
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        true
                    )
                )
                add(
                    Music(
                        "ms012",
                        "Handsome",
                        100,
                        "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fmusics%2Fhandsome.png?alt=media&token=770408f8-69bb-423a-bdec-d47338d19542&_gl=1*4sn7ya*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4NjQxNS43LjEuMTY5NTg4ODUzNS4zOS4wLjA.",
                        Time(0, 4, 43),
                        Date("13/04/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca006" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        true
                    )
                )
                add(
                    Music(
                        "ms013",
                        "Sharks",
                        200,
                        "https://s3-alpha-sig.figma.com/img/5822/25eb/7dd77abaa2a7d741d2ea6db5f4748610?Expires=1696809600&Signature=jgxK8oPHwwnuZal1d5e0kxCTHuKcDsUugJ6k28DWU-Z7dkrqqV2qHFkRslV-RWondMEFN4MmIfeEa-eUv47rtdrSwsvyGdQA56cBYHOC-FdK3AlY5ozlba15YuNNRWPMm45o3nkCcWwJUTyhFCtJjGVnG~v3nW1qJvsAizhig-KSgtytOtGJRGWkd~EdRo8CKmvQt-qYNe7uwbT2dJYF3eu2mQ7Wa0m22r0wLw4KlEbDw44Zt8YvBvYH~YFdKwDO-XhCbdJZUB2hfq1MEZ3mwLzkcgUL3642mRLFu4ADXyyLe9zReXlBYpFPTdv6g6SHAFi7455OmR-dsXu4tW19ng__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 3, 35),
                        Date("9/08/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca007" },
                        ArtistsData.dataArtist().first { it.artistId == "ar003" },
                        false
                    )
                )
                add(
                    Music(
                        "ms014",
                        "621: Reid Hoffman | Surprising Entrepreneurial Truths",
                        15,
                        "https://s3-alpha-sig.figma.com/img/0bb2/dbba/64cde090ac8a56c1e620b12d7e7f51a6?Expires=1696809600&Signature=LA6Oo1nvzfMQA3Qn0i53KD1ISs83O3f08HqaCl3IwJ12YghgDSDVnUtKY0BBHfld~HTqJhHe4EfEcXjUaN50U6z4UBprBOJBVwfHi6HQe5Xf7-mBFpPJFGHiWSl5Z8W~fej~1-F5WAMF~cTJoqv1A290C4TZIgzONRPLxDC51L1P4kvrhUldTsPcln4OpQtwdglAvStgZ3FSC5LDEYgpcBe5--ja5aQwMaBRmqL7R-wp1-QFc7GjWfyrPRlhqycF55ALE1URUp3-QvwdZpbhZa1Kc5eMYbCZ2SvMiX~KCVP1qmCkg~xKiZTGySpnKB4iSvFtVSBSaCVwpQyyCnXD5g__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 28, 25),
                        calToday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar007" },
                        false,
                        "https://cdn.pixabay.com/audio/2023/07/03/audio_80cd47077b.mp3",
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
                add(
                    Music(
                        "ms015",
                        "Pain",
                        692,
                        "https://s3-alpha-sig.figma.com/img/692e/8125/07e12ccaff200ac1214f42ac2c466f1a?Expires=1696809600&Signature=hRojTeDSrSeP8RyOQYCGnBfFFeQX1diE~dZrhzAS0NPUNBkEmdgMktVAD-FQsN9dsRVk2R4V~Hn7bbZgqPYUjq9Lfoz8FYdGIP2zlQjAJ0PPyjBF1noNd1mHcv-IXz54B3v9Ly8z9lbBulxIqHR3c6lhekUyProP8jPBBvwMEQMjfaNh3Pt2GcQlxlWscdHyTe3kY9JD9Nl7kBNzz5wpju5cAs0G3lWIloCU1-n65K0wcLZ7abjOJKnWg-SECo696A51ETdPerB3cxNhrc9yczAuTAnM861pg9vdvSKTwT3pBKGQ3C-fdNq4vJXqYfvBNskU~KDxFS0WIVxd4fRWBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 3, 23),
                        Date("15/04/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca007" },
                        ArtistsData.dataArtist().first { it.artistId == "ar004" },
                        false
                    )
                )
                add(
                    Music(
                        "ms016",
                        "Motomami",
                        1203,
                        "https://s3-alpha-sig.figma.com/img/36e6/bfb5/58b9b28e29d52bd7f484b799db3341f6?Expires=1696809600&Signature=Dl9fVg~AZ1X70QegXpHFCm1vl-0r95m6G0A4Co3s9VNUq~YZ-Cd7FH-4aRdLmU~7v6SyCd-6aYmMlccqh8v4kmwUVHW6TfJ-uYXLa-V5jNTLZfUe2n72DnjZ0m2AgT6Czng3-vJos54uNN9S0mtuvzJLQ~PXit3p7th3iju2wcV33RHC0Zhkkv5BeKPBlUuRKrrnVao3ME0UkHwBOm5VYzKO9-fQAkSC-5VqJT8Se71DWz4jUGxdoXE-lgGgoYTE2t4P9h8LoKR1XY~87WHCiDJ2nRO6x8o43~hDlWxe6~QVIZ2Woiy0ok47xgDR4aIl6bMje5nj73IT-EYtQkY2sA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 3, 43),
                        calYesterday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca008" },
                        ArtistsData.dataArtist().first { it.artistId == "ar003" },
                        true
                    )
                )
                add(
                    Music(
                        "ms017",
                        "Positions",
                        982,
                        "https://s3-alpha-sig.figma.com/img/9c08/fb16/f1449e135ce592b7fb46860f97792e50?Expires=1696809600&Signature=X73kPNym8btwytntkzry0UQNUC62LmrLJ2uQXCsLuUeC5C9E7mEf3nfO5XxrDE8GOcyinAOlB2Zy4ci9Jas56KFdzdLB~e2Qd2zXLD7X5ynDFDI-FKcJA0fWBwlmgtBuzWVwfRpAsEllNamGzMayhwSgHFUO8X5AxYJICarQq8n1ZZ8dmJ1918ZEtDaNrTgoArWQILicAXODbhQ4~Iv3HlIcuNA5G6yIi67S-LaMM-ZNSgkjBv2XstQfVmxNcLXrXF3W2jYS-xuR1k~p-LJ~HWTWdfKE1h-QPVAXomaenHJYf-thwc4F1GqCAsCjKYF24LrnOjg6kMaUi-n-9Y0l2Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 4, 23),
                        Date("11/09/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca008" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        false
                    )
                )
                add(
                    Music(
                        "ms018",
                        "Firework - Acoustic",
                        32,
                        "https://s3-alpha-sig.figma.com/img/a378/e53f/76aed56f8d8581b227777d466c0b5116?Expires=1696809600&Signature=SwmgG~146zalGlwECyAjg-h7wZrFjvU4fNO9BoO-twcAysJqjxlC4Lucr-yYZILU9Gl2~5BmL3gsBRz8VWJid3nruVVh52-37oSZZKJa3fZoxojt6anngtUZeOCD8bLlOAUcpE0RXrNGwXZFBtehLy7kLt1FkIvGk2Pmkg4B8ZgUhw19uRGXZeuA78ntCIMYmL~9tDNpSIUsmT1Ts8a8broJyR1fH2tKu03~XLrHXfJ9cysMVyzzRrHukGMaNzh34gAxDwiKi30ytAnV31-2G~2gYglH~iyuZZsHF6VKjmvd~1NLtBFRV9RG9oUhCaDGNt8WSO-MZa5pEr8JgJhz9g__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 3, 56),
                        Date("06/03/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca009" },
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        false
                    )
                )
                add(
                    Music(
                        "ms019",
                        "Roar",
                        2031,
                        "https://s3-alpha-sig.figma.com/img/2b42/112c/fc6f30c195474a93f691153f3d390bcf?Expires=1696809600&Signature=XgdllpYOoSwarPeePzQpx~K-3wA6EeRTwMlZ9HUU5Mdl8ogTyFYErgiOKueFna1LZxcN1rya2EWnK317PchKLmFbT-6sGzYokkg-ttrQVhZ7Pf5PsVkVIrx6WLxA-oyi9ankFrYfM7NIjcvhhwDR-18rnpXPerWEgnchRYwDxNSpOftEnUTEmBNEeJ8PB-sW9CDwrKc1Iw~ksplCMnU1wdao0AYkzMpNCo8F95rOTo53HQ-FpAkqxTvIGgnV1uL7sCjkmLRdrAgwxk8S~fBPb7EaX48cG7ijpyjtJnfLOq1A5ce5BpONzES4CjNKlsls4RhnulJTU7VnPLIucC1a3A__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 3, 23),
                        calToday.time,
                        CategoriesData.dataCategory().first { it.categoryID == "ca009" },
                        ArtistsData.dataArtist().first { it.artistId == "ar001" },
                        true
                    )
                )
                add(
                    Music(
                        "ms020",
                        "Teenage Dream",
                        92,
                        "https://s3-alpha-sig.figma.com/img/01b3/9d00/9dbf61695471ddeda0845c05273cd4ee?Expires=1696809600&Signature=nxNcIB-DLS9OheVHS~RZmi4BB6zb3z4rVAisTyIrnsamv6fBVzf21vvCqEjqLMUKczbUd9n1bWIn0hWlHodnchKoUQ2MAadVWwMuVSDo0K-tTDtLUr6caCuHOPCo~zod6UGIHbiAJJuurD4eQ9Tn-zQrw66HsGQST9vF3FvAOoYsMeK0XEM5fDAzGRgLHFm6e5RPYzhm4A-NAxuGJOfpZQ5a-xskDUAWlKKRNNEZNw~5ftN-EvbAl1zNq1gWHeAjfIy~-PimE6Jsne0hLkoLW42AknZXEVbzPvTVDaYEierCMeD9-JVhmpN9Xb6fhyH2avLqQpQi80pIzm2kj7IysA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 2, 34),
                        Date("24/03/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca010" },
                        ArtistsData.dataArtist().first { it.artistId == "ar006" },
                        false
                    )
                )
                add(
                    Music(
                        "ms021",
                        "Last Friday Night",
                        572,
                        "https://s3-alpha-sig.figma.com/img/5822/25eb/7dd77abaa2a7d741d2ea6db5f4748610?Expires=1696809600&Signature=jgxK8oPHwwnuZal1d5e0kxCTHuKcDsUugJ6k28DWU-Z7dkrqqV2qHFkRslV-RWondMEFN4MmIfeEa-eUv47rtdrSwsvyGdQA56cBYHOC-FdK3AlY5ozlba15YuNNRWPMm45o3nkCcWwJUTyhFCtJjGVnG~v3nW1qJvsAizhig-KSgtytOtGJRGWkd~EdRo8CKmvQt-qYNe7uwbT2dJYF3eu2mQ7Wa0m22r0wLw4KlEbDw44Zt8YvBvYH~YFdKwDO-XhCbdJZUB2hfq1MEZ3mwLzkcgUL3642mRLFu4ADXyyLe9zReXlBYpFPTdv6g6SHAFi7455OmR-dsXu4tW19ng__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 4, 45),
                        Date("01/02/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca010" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        false
                    )
                )
                add(
                    Music(
                        "ms022",
                        "487: Mike Rowe | Dirty Jobs",
                        42,
                        "https://s3-alpha-sig.figma.com/img/bd8c/dd28/bedb56fafecef4a4741a242dd3d897e9?Expires=1696809600&Signature=oP7k50nHTOhCCgr8~TAQ2c8KCVKNCQWObKqNbIAfE1U4DSQ71-JjgSesVJ827WikkBT5utsBwEqx8LQEGpuAY-3mmKVUe~JQbndKjTmP1gei8xw1WGD2oaSy2zZqZH~FZQNdm-2MFTYx7ifgaidcth3rqpd8PpE2~o7GS4GM3ig6mAVXSbtDfYwpa6IFl3Gnu9Cr1Ji1s9peuHtwPbChY4vBi1lnE8hg1qr3i4omRwZSILYfr9pIwrkdY8LepRTTtoYQzzIFP2sQJanhpjl4sYvQXcZw~u5Qw8IM2h4-wbc9rqdCUYsYJJEMqGPZMe2WxozCKxhe7lUWfbNM1m71zg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 50, 53),
                        Date("23/06/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar002" },
                        false,
                        "https://cdn.pixabay.com/audio/2023/07/03/audio_80cd47077b.mp3",
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
                add(
                    Music(
                        "ms023",
                        "938: Tom Wright | Billion Dollar",
                        15,
                        "https://s3-alpha-sig.figma.com/img/659e/0b9b/3cc4cdb73fd94445f46a2de36086d8f6?Expires=1696809600&Signature=mFsA6GDE5LotY-zThBg6FgYMjNBuQ-wu8QFTCwBdiVssTxT4qoxgA9naYLIyp5GoWm2mwMqTkDhE7sKsu4ochBogQnxgsGOPH1-rFGnOb39EWsggbMZpqgZnpUCvsmn0YbSn1C6nlrOqXutjR-irgj9OV6QW7xPpkI9Vz2XwYCGXGB1uW8-bV~WtorOWBBPrxaVIfWtoc4dL6eLEYlWmqNj1DszMF092cmLCbthcfqY-nXqjizMlOyp3HVf657QJAutFMYO2Ogn9Pu~zLy2uechaFOmRU1pAeprVL~tTgqVwbPBZ4FZEb3~B4NE4PSjmYg376xgDnUGO6qud2a1KPw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 40, 33),
                        Date("05/02/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar007" },
                        false,
                        "https://cdn.pixabay.com/audio/2023/07/03/audio_80cd47077b.mp3"
                    )
                )
                add(
                    Music(
                        "ms024",
                        "610: Bill Sullivan | Pleased to Meet",
                        292,
                        "https://s3-alpha-sig.figma.com/img/4743/c387/a4ee427a3f240139dc1f70f94ffe5351?Expires=1696809600&Signature=DU16Sl5VGBs-W1HeSvDDpD2TNF6MdxNdnsWvT8SOkTomAMY84tXo2LFPKR03yFtoYEVJu3Fd3O8kbZ-FM98a7g6KMoj7aiLh0RALyNzlaC6FPPh2Dwgr1k~PnrnEGgQlJwSOgFglk8r83QCS-ggHZMXCPHtUXV4auC0MKg1YWbhjrdR57hjYnwshwAH9T3c6DNhUL4EfW2f7aBFSmBW5zVdwd61uX9PKXQiKDXJ~4QINOjR-7cnL~IOf8KOFK128j1pxfantZdutWnqS7GTeD0YSEwb2hLmADE0dKCdy6Lp78UQsra4t6jQMYHAEr103tEmd8N~DVNQWefIksg8WYw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 15, 35),
                        Date("10/05/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar008" },
                        false,
                        "https://cdn.pixabay.com/audio/2023/07/03/audio_80cd47077b.mp3",
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
                add(
                    Music(
                        "ms025",
                        "688: A-Rod | Still Having a Ball After All",
                        292,
                        "https://s3-alpha-sig.figma.com/img/2c49/b0ed/e234f658c60075523c1ddbadd5543d8a?Expires=1696809600&Signature=naJ8KZveokhzm0YXkmyZkD9Jpx0L2j8szCrUWb9RyW9sWToZNVbjQmHVa~KBqN8O1bIBnvl~wlJu2zZo59DDzlAk3PYz9P01q8Uetbv60mgL6rWKXwiTpdmwY16P~ch3CPaWvHH9QjQodtYT1ewnSLKo1nifzvdecvafOKADTimxs-vv6rpwZOHtVC2VQuGMmtWaSrnYggROb8rqWLVJR5XLKCaUfT22FSyQEM8YImuxUBO4-q2YBdxeD-fPAdanCUt6eS9azeICaeRgmpxNZobqwWmfm4LM6f50ZAI0~czAAddPEL6VplLDJLxDwiBdEhy5gAz8sARSMCyV~ItRAw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 25, 35),
                        Date("15/07/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar007" },
                        false,
                        "https://cdn.pixabay.com/audio/2023/07/03/audio_80cd47077b.mp3"
                    )
                )
                add(
                    Music(
                        "ms026",
                        "831: Desmond Shum | Wealth, Power, Corruption, and Ven",
                        292,
                        "https://s3-alpha-sig.figma.com/img/70a7/4f46/b2666580eb98313b4d05502d23eafc39?Expires=1696809600&Signature=Ypn75Dq6Z8gmSEofaKCBeWQvvjKxEvRgqjh6NQLpMOu5PanQ70KT7L0q4W0eRWhHaa5xucbDn6wk2B1fpH4FHT~evw5MtMQDsQt2U0Bu8m6EWcYb9Z9a8r3Yh9IDSzm~OC7qikG9ajjI9xxP7-3cOfGcGkPIeYsFlptG6NpyA37ze-fvenFf92m9E75eQAvf7C-NlboL~dno2UcTwW~-lZc7YGtMN5~nlbbQTfHBioKpcTlvb~YzAZR9vapb9gqQ4bwpeIRUoiIIhNhY0AD7jttUCk3QIHfBENh8WqfwLkuqOoyX6ceoTNhyEyRSlRuYHr5dtPKESMlK8fZTijWM6w__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 32, 12),
                        Date("12/08/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar008" },
                        false,
                        "https://cdn.pixabay.com/audio/2023/07/03/audio_80cd47077b.mp3",
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
                add(
                    Music(
                        "ms027",
                        "396: Chris Voss | Hostage Negotiation Tactics",
                        292,
                        "https://s3-alpha-sig.figma.com/img/d314/77c1/0dd76442332f9af797ad4cf0c673f073?Expires=1696809600&Signature=XNWHzMF~Dn2P7SlxD7DTnOK-fpN4pQtnSlJt6zekVTuRMqunAbj7BDpynCKWZSktziuYP4XrrxfxX5Mk31bQs0T0tCtrpnV9p~-xUtazlMCvj4f3T4qkb4NVtQ8sK9l8vv0QIQiNC2Og0W6799RZJhEi6si21TrbLjdEmhKSx5e2zZEd3-kKhQ0HVXuxPTfR-DehZg~2-CLmuLGnO~~6ypkXZ8vYrdi1SmqFCPTQtWuMWxnwqBZTq~3JKTwdObP8k9RUfHVNyUs0oEaQ~KlP2-oloFm4Das505kL2huULpiDcuBUwowtBT~pkREg0MCgy8JqKeheihHdoOO8XwCY8A__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        Time(0, 23, 32),
                        Date("05/09/2023"),
                        CategoriesData.dataCategory().first { it.categoryID == "ca002" },
                        ArtistsData.dataArtist().first { it.artistId == "ar009" },
                        false,
                        "https://cdn.pixabay.com/audio/2023/07/03/audio_80cd47077b.mp3",
                        "Shaquille O'Neal (@SHAQ) has been retired from basketball for years, but he’s still got his irons in plenty of fires. Here, we’ll discuss everything from superheroes to law enforcement to business to sports to podcasting to — yes — the Flat Earth Theory. What We Discuss with Shaquille O'Neal: The real-world experience Shaq endured preparing for a political race in 2020. How Shaq assembled what he calls The Panel to help manage not only his career but all his important life decisions — and how you can do the same. Why Shaq doesn’t consider himself a celebrity (and why that still isn’t a good reason to bug him in the middle of dinner). How Shaq manages his emotions so he stays non-reactive on and off the court. Does Shaq really believe in the Flat Earth Theory?"
                    )
                )
            }
            return dataMusic
        }
    }
}