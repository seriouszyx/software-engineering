from snownlp import SnowNLP
from snownlp import sentiment
sentiment.classifier.load('../train_model/sentiment.marshal')

comment = []

array1 =[]
array2 =[]

count_num = 0
count_sum=200
  
for line_data in open("eva_data.dat", 'r', encoding='utf-8'):
    
    comment = line_data
    
    s = SnowNLP(comment)
    rates = s.sentiments    
    
    if (rates >= 0.5):
        eva_label = 1
     
    else:
        eva_label = -1
    
    eva = str(eva_label)
    
    f = open("eva_result.dat", "a+", encoding='utf-8')
    f.write(eva)
    f.write('\n')
    f.close()

for line1 in open("eva_label.dat", 'r', encoding='utf-8'):
    array1.append(line1)
    
for line2 in open("eva_result.dat", 'r', encoding='utf-8'):
    array2.append(line2)

for i in range(0,200):
    
    if (array1[i] == array2[i]):
        
        count_num += 1
        
correct_rate = count_num / count_sum

print(correct_rate)