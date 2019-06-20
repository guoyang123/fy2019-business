
注意：
项目部署到docker中，所涉及的图片上传等全都存到了docker虚拟机中，访问图片需要访问宿主机，因此
在运行容器时需要用-v参数，将宿主机与虚拟机做目录映射。宿主机就可以读取docker虚拟机中的内容了。
启动项目:
docker run -it -p 8888:8888 -v /neuedu/qrcode:/neuedu/qrcode  monotnous/fy19-business:5.0-snapshot /bin/bash


