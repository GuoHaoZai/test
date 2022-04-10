{
  "name": "test1",
  "tags":[
    "test","aa"
  ],
  "this_name": "${this("$.name")}_this_test",
  "this_tags": "${this("$.tags.[1]")}_this_test",
  "sex":"${enums("man","woman")}",
  "user_name": "${user("$.name")}",
  "user_age": "${user("$.age")}",
  "addr": "${addr("$.first")}:${addr("$.second")}",
  "tel": "${tels("$.[0]")}"
}