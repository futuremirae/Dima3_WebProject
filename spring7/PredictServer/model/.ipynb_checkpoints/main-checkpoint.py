# package import 

# 설치 파일 : fastapr. uvicorn, pandas, numpy

from fastapi import FastAPI
import uvicorn
from pydantic import BaseModel
from starlette.responses import JSONResponse
import pickle
import numpy as np 
import pandas as pd 

#Model 생성 
class Item(BaseModel) : 
    petalLength : float
    petalWidth  : float
    sepalLength : float
    sepalWidth  : float
# 앱개발 
app = FastAPI()

# let sepalLength = $("#sepalLength").val();
# 			let sepalWidth = $("#sepalWidth").val();
# 			let petalLength = $("#petalLength").val();
# 			let petalWidth = $("#petalWidth").val();


#@app.post(path="/predict", status_code=201)
@app.post(path="/", status_code=201)
def myiris(item : Item) : 
    # pickle 파일 로딩
    with open('data.pickle','rb') as f:
        model = pickle.load(f)
        dicted = dict(item)

        petalLength = dicted['petalLength']
        petalWidth = dicted['petalWidth']
        sepalLength = dicted['sepalLength']
        sepalWidth = dicted['sepalWidth']
        target = ['setosa', 'versicolor', 'virginica']
        ary = np.array([[ sepalLength, sepalWidth,petalLength,petalWidth]]) #
        pred = model.predict(ary)
        result = {'predict_resulta' : target[pred[0]]}
        print('==========예측 반환값============',pred)
        print('==========예측 결과값============',result)
    return JSONResponse(result)

if __name__ == '__main__' :
    uvicorn.run(app,host="127.0.0.1",port=8000)
