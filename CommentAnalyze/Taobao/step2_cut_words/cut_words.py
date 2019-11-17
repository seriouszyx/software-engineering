import jieba

jieba.load_userdict("SogouLabDic.txt")
jieba.load_userdict("dict_baidu_utf8.txt")
jieba.load_userdict("dict_pangu.txt")
jieba.load_userdict("dict_sougou_utf8.txt")
jieba.load_userdict("dict_tencent_utf8.txt")

stopwords = {}.fromkeys([line.rstrip() for line in open('Stopword.txt', 'r', encoding='utf-8')])

tao_bao_comments = '../data/tb_comments.txt'


def get_data():
    print("正在解析数据...")

    with open(tao_bao_comments, encoding='utf-8') as f:
        result = []

        seg = jieba.cut(f.read())

        for i in seg:
            if i not in stopwords:
                result.append(i)

        fo = open("../data/data_full.txt", "a+", encoding='utf-8')

        for j in result:
            fo.write(j)
            fo.write(' ')

        fo.write('\n')
        fo.close()

    print("解析完成!")


if __name__ == '__main__':

    print("进程开始...")
    get_data()

    print("Done!")
