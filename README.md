# yaml 가이드
[VS Code](https://docs.aws.amazon.com/toolkit-for-vscode/latest/userguide/welcome.html)

```yaml
      Events:
        HelloWorld:
          Type: Api
          Properties:
            Path: /hello
            Method: get
```

# Layer 만드는 방법
* cp -R node_modules layer-dir/nodejs/

* cd layer-dir

* zip -r ../layer.zip .

* cd ..

* aws lambda publish-layer-version --layer-name express_module --zip-file fileb://layer.zip --compatible-runtimes nodejs16.x --region ap-northeast-2


# SAM 프로젝트 패키징 방법

* sam package --template-file template.yaml --output-template-file exported-template.yaml --s3-bucket itsmung

# SAM 프로젝트 배포 방법

* sam deploy --template-file exported-template.yaml --stack-name itsmungStack --capabilities CAPABILITY_IAM --region ap-northeast-2