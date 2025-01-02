package com.app.hearme.data

import com.app.hearme.model.Category
import com.app.hearme.R

class CategoriesData {
    companion object {
        fun dataCategory(): ArrayList<Category> {
            val dataCategory = ArrayList<Category>()
            dataCategory.apply {
                add(
                    Category(
                        "ca001",
                        "Chart",
                        "https://s3-alpha-sig.figma.com/img/9c08/fb16/f1449e135ce592b7fb46860f97792e50?Expires=1696809600&Signature=X73kPNym8btwytntkzry0UQNUC62LmrLJ2uQXCsLuUeC5C9E7mEf3nfO5XxrDE8GOcyinAOlB2Zy4ci9Jas56KFdzdLB~e2Qd2zXLD7X5ynDFDI-FKcJA0fWBwlmgtBuzWVwfRpAsEllNamGzMayhwSgHFUO8X5AxYJICarQq8n1ZZ8dmJ1918ZEtDaNrTgoArWQILicAXODbhQ4~Iv3HlIcuNA5G6yIi67S-LaMM-ZNSgkjBv2XstQfVmxNcLXrXF3W2jYS-xuR1k~p-LJ~HWTWdfKE1h-QPVAXomaenHJYf-thwc4F1GqCAsCjKYF24LrnOjg6kMaUi-n-9Y0l2Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        R.color.green_500
                    )
                )
                add(
                    Category(
                        "ca002",
                        "Podcast",
                        "https://s3-alpha-sig.figma.com/img/4743/c387/a4ee427a3f240139dc1f70f94ffe5351?Expires=1696809600&Signature=DU16Sl5VGBs-W1HeSvDDpD2TNF6MdxNdnsWvT8SOkTomAMY84tXo2LFPKR03yFtoYEVJu3Fd3O8kbZ-FM98a7g6KMoj7aiLh0RALyNzlaC6FPPh2Dwgr1k~PnrnEGgQlJwSOgFglk8r83QCS-ggHZMXCPHtUXV4auC0MKg1YWbhjrdR57hjYnwshwAH9T3c6DNhUL4EfW2f7aBFSmBW5zVdwd61uX9PKXQiKDXJ~4QINOjR-7cnL~IOf8KOFK128j1pxfantZdutWnqS7GTeD0YSEwb2hLmADE0dKCdy6Lp78UQsra4t6jQMYHAEr103tEmd8N~DVNQWefIksg8WYw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        R.color.purple_200
                    )
                )
                add(
                    Category(
                        "ca003",
                        "New Releases",
                        "https://s3-alpha-sig.figma.com/img/cc14/ec68/69b57d487d0805bb83aa02d8eac8fd8e?Expires=1696809600&Signature=Vr4ulwoBlINHkSqxH7KZhM5jU-kmvo2ESZK-NYIXWRAUYhDR7nV200Me-8hUpH2pv9Jlpv3ZZROnYZF~sZNUdGaG0DWdT~czswpFrOxNhfHU-T~4vZh6Wx6JwEJcMKfPa7n4OuXPoYQ8TO2agiJwzVC3TSjfnlcIwJ3pn23hsBhb4el4SLj0LkL~EkXBGhWVMgFICF6NRdt1nG0a7T2dizwjeG-v10pdLG7HCklVzG0E3IkvsPcDk2qJ72guCi3AgZF8DOO1fuE3wQg4FKUE0UmbW~SuFDgwXRZdy1srZ5BSTmq9oZcfpuGTcxbV2FWySE1j-fIk6R3stIK4c5YSLw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        R.color.greyscale_50
                    )
                )
                add(
                    Category(
                        "ca004",
                        "Only You",
                        "https://s3-alpha-sig.figma.com/img/38a3/a083/192d01382a6b2593cf87b322f037c2be?Expires=1696809600&Signature=UVX3EuQjH7mkYtpBy5s-mUJmZyf-639d1cMx8Icrntg7616nCZLpRL8jwm8RrYLY1B~gDqWbnIXwdgB1~SxHYbZ-WSdKHjq66ICz64Q4riSTadgUGFpxKFXEdTPoOeeqP61RlW4oTTlBVbj7KtuFHMt5yrbDH8bWoPzQIFaugvMCFn9EV7oGNcmCC34WeyzLrZH2VqW1mq074v211eCfle0c4eF8IZkCAcBwq51u9qK-2vzQ~pF4Y-KNPb1v0vrbUm6vgWECgvJjAwxqKuhZ2DjEIPCH0eoiP7Abk~Lnu2dJLj1gfKQpUDMeqZNiCqiB2abyAgpzSg1Numz7kxoYCQ__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        R.color.teal_700
                    )
                )
                add(
                    Category(
                        "ca005",
                        "Pop",
                        "https://s3-alpha-sig.figma.com/img/b720/8ee4/92d2f58023ddb67411ab73f18a6358c4?Expires=1696809600&Signature=CY~-gp~mNq6vgqEVgOcRf1y4yfnvWQgj0IcDPPsnBGEi0FNvDDZEmLlDm33yPTr5NOwWo7ilriFbzafN8V8vWOmuw-KccnhMTUIVyRtMCXYA5VBePHErcbXAaon75~cFRIe~2l515exNiv0-zs6ft2WLv1hFQjm18ZMIk5wEEqwag4KyAhN09ej~Ajb7jTae-w-rdFdTmWSqAFhx2yF5PVYtoYFfcs4Y-hh5HYJWDHhBJJf0VgTUWbuYesp3k~msMuMAMWDg8hebFIqqRESZP6p0uECdgEZvNh-roPqLBGr1fP1U9l3swKM03lznobtN86XBb26wittvU9dsNGKnGg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        R.color.purple_700
                    )
                )
                add(
                    Category(
                        "ca006",
                        "K-Pop",
                        "https://s3-alpha-sig.figma.com/img/1b83/3a8a/36fa079ab99ff09b79d558f10877e489?Expires=1696809600&Signature=kcN4YiVU56GhI59io74n2lEfG7-O8ocaayQM-WW0vOejLiKgd4E~8hcczcF3vG4cyybSlZXw7gT7SV2XzSl8YKboF7YFL01XNKlY2L7HEzj3rK60Yn1qH5bAxWY2Pl5EHimgrxrNA9F-SjDZF~ZeZOIQmaO~Xy28AFujTJgdpXi6tjJo4uf6m1ZmJyoPWOcWfTu3svYrrPEq9viVF9QjKqj7hLr0j6Ms2XQzHkUh7Qcxmo7nn7fmTDnCenuMzU4JRhYGh8IgzdSqvCCWnqqlj0GYXPPsZMZtkAQWeYPdKOyMT2lLpNFG~p762psAJjNM14~7N9BtxuhF3sy0PWGjiw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        R.color.teal_200
                    )
                )
                add(
                    Category(
                        "ca007",
                        "Rock",
                        "https://s3-alpha-sig.figma.com/img/0a19/8ce8/d15487ba7abf26ba4ef1309b1b116a85?Expires=1696809600&Signature=XvEYXTsGDV5NKShlmHTPXuM71fpEInxGd2MBcTWJjEnM~o8Kjq8ZA1TfdsQiwzW6EOccNCheogoghWaDYyIrB1KijpMG5L5vF8ofk6~~KsAgNDOg6hcjKbczDjArjkpT47YtkcuYLi166LXd9TuYgUluv8BWauqikNQQ98ucS0RgV2ypcEsRYL9VOI-B2rMvfR~7q6HVOGI6pjZyeueFIHECyW9llSJ-fDaWPHOR4zxb2mGak2-KqQ0pV~yA3HnS7bmqlncMDtMwcyUDpQV7u6TZBYpf8JeQqZOnLd4XVmCZRqt1ZU~w-I8FQ1Irztb6GaXqbXHyHGDMCMw5uRTbgA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        R.color.greyscale_200
                    )
                )
                add(
                    Category(
                        "ca008",
                        "Hip-Hop",
                        "https://s3-alpha-sig.figma.com/img/e1fb/3848/41820a2bfc8b40a474641a6e65b6ca4a?Expires=1696809600&Signature=DfS47RTwTncFpH5iP25kY9QTG1kNc0k6842mr7NKrQdgIejGUre5~sp7LNrFH2MGWuCfThlz50C-d04NYUTO2pj7J~~0PaZ~ClyYq5iVQYT8RAWk7EH6BG6FDm2fgtcf04AIOdBVjXrHWBZBavprhwXu0DrUOSg-Sf2Zx7OCmT4QuBGDqyTxfhjGG2-r1jmkP7Flwj1PDuszWIEH0~XATHbyS3v7pCoCLf85Dm9SQTnNve7h-gOV~brG4SJJxQjsDv8mOG4qtgoBw1MEJLqj8Lj3WDIm2SyagXPsOySZtnWkwlF68SLqXVaR~o6LLcLiXlqXNV94ghck5cdqsgcTJw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        R.color.purple_700
                    )
                )
                add(
                    Category(
                        "ca009",
                        "Jazz",
                        "https://s3-alpha-sig.figma.com/img/e5a7/183b/697b5c47c2737fb8cc17d83f5e4a1882?Expires=1696809600&Signature=b8ZgC-xkOZ9I57XdudEunbEUehJzgQ8mka8gWtplv3LB8puW9~93x8EVc2IFPFC7f0n9ZgpXA7TwkZWIfTOTvZbqrRv9yqv6AaKY5~PG0tq~-EI7V7e7-lekvrjVeeyjgtQYUiCt4lmqYVcIVwm~GWPEsGjJOgdNU9Puq5c~dFNAfv47UJS509YmZgPplVH7J5OfPgXx8uWtQT5FVWcp7E~4KwqsfVa8CG32UPB7ID3bDMdFYoL-D9CQJ5dSsYHIVxX9JofjFRDYlB6PolSsjHp4KBY2bHleCWvvke5V~L2f01TsUAt2O7yvHq7HGUtDQYjd5Mzjiczg3zEQpcijiA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        R.color.purple_500
                    )
                )
                add(
                    Category(
                        "ca010",
                        "Romance",
                        "https://s3-alpha-sig.figma.com/img/e7ac/3b09/5c5ebc9a905a527c702f7dcbedc13ffd?Expires=1696809600&Signature=D1MI8JEIbOifcTBmzNMkTnIeDXyvZaLWxhpH94VzolWRJvOMHz5VEFqbPGqoNs5yadQPGxKTkyWpo3Yr8XnH5Phv1SVFwO2GPYTmk2ipJSkKFbK4EsdnVxrE9pwqw~L3w4-HbScb2u4CH1C90Ggx3HqVG6EbToe6DmHvWh-oJHpFsToWYflyxy5~X7LgmFPUVwq1MGNep5RRGLxzfUNgbYFPNaLA5m6zi-kc4Ow9LHdCGEYkQKS-3iJl-q9kt1p0O3jslRswuEFRgHsvPgiD1Dzq3kzhcfj8GwArGhJGAlScobA7NFD~KGw05cpy7gBENSTeZd14UKL5U9fb1i3C2Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                        R.color.bg_button
                    )
                )
            }
            return dataCategory
        }
    }
}