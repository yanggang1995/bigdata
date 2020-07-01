# -*- coding: UTF-8 -*-

import ProxyReptile
import json
from kafka import KafkaProducer
import time

HOT_TOP50_MOVIE = "https://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&page_limit=50&page_start=0"
PROXY = ProxyReptile.Reptile()
KAFKA_PRODUCER = KafkaProducer(bootstrap_servers='192.168.90.95:9092,192.168.90.74:9092,192.168.90.96:9092',
                               value_serializer=lambda v: json.dumps(v).encode('utf-8'))


def load_movie():
    subjects = PROXY.get_response(HOT_TOP50_MOVIE, "1")["data"]
    index = 1
    for subject in subjects['subjects']:
        subject['hot_index'] = index
        index += 1
        # print(subject)
        KAFKA_PRODUCER.send("db_movie", subject)
    KAFKA_PRODUCER.flush(timeout=60)


message = {"cover": "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2594916975.jpg", "cover_y": 2222, "cover_x": 1500, "rate": "8.1", "is_new": True, "id": "33420285", "title": "\u6821\u56ed\u60c5\u5723", "url": "https://movie.douban.com/subject/33420285/", "playable": False, "hot_index": 1}
total = 0
print(123)
while True:
    for i in range(50):
        KAFKA_PRODUCER.send("db_movie", message)
        total += 1
    print("total : ", total)
    time.sleep(5)
    KAFKA_PRODUCER.flush(timeout=60)
