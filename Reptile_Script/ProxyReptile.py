import requests
import Entity

class Reptile:
    def __init__(self):

proxys = Entity.LoopNode("http://49.233.217.149:18090/proxy/reptile")\
    .set_next(Entity.LoopNode("http://192.168.90.96:18090/proxy/reptile"))


def get_response(url, is_json):
    param = {'url': url, 'is_json': is_json}
    t_url = proxys.nextNode.data
    print(t_url)
    print(param)
    return requests.post(t_url, json=param).text

print(get_response("https://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&page_limit=50&page_start=0","1"))