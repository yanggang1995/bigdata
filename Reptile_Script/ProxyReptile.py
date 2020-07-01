# -*- coding: UTF-8 -*-
import requests
import Entity
# import logging
import json


class Reptile:
    def __init__(self):
        self.p_server = Entity.LoopNode("http://49.233.217.149:18090/proxy/reptile") \
            .set_next(Entity.LoopNode("http://192.168.90.96:18090/proxy/reptile"))\
            .set_next(Entity.LoopNode("http://192.168.90.74:18090/proxy/reptile"))

    def get_response(self, url, is_json):
        param = {'url': url, 'is_json': is_json}
        self.p_server = self.p_server.nextNode
        t_url = self.p_server.data
        print(t_url)
        return json.loads(requests.post(t_url, json=param).text)


# proxy = {
#     'http': 'http://120.26.199.103:8118',
#     'https': 'https://117.88.5.118:3000'
# }
#
# headers = {
#     "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36"
# }
#
# print(requests.get("https://movie.douban.com/", proxies=proxy, headers=headers).text)
#
