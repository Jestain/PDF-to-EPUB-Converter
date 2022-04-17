# PDF-to-EPUB-Converter
A tool to convert pdf to epub without changing the PDF text format and internal structure by Zhijie Wang,Chengyuan Luo,Yonpan Sheng
<br><br>
**Note**:We implemented both on-line and off-line system to support different demmand like online references call and embedded function call.<br>
Our development is base on B/S framework.<br>
# Overview
We present the front-end for operating,the converting result comparation and adaptation of different mobile devices.<br><br>
Heighlights:
- **High Degree Of Reduction**:establishing a coordinate positioning mechanism and independent style libraries,reducing the controlling <br>
scope of styles,achieving accurate row-style matching,optimizing the effects of PDF with complicated text and pictures.
- **Good Adaptability**:achieving mobile effect consistency,dynamically adjusting the display effect by establishing a screen frame,<br>
using relative unit to convert the content,which will adapt any mobile devices.
- **Can Be Run As a Stand-alone Tool Or Service**:establishing an offline-on-line two-implementation system,providing high-quality <br>
conversion services for stand-alone/other applications.
- **Multithreaded Concurrency**:establishing a multi-core and multiprocessor concurrency mechanism, providing distributed task scheduling and<br> 
providing an efficient basis for converting massive PDF books into EPUB format.<br>
![](https://github.com/Jestain/PDF-to-EPUB-Converter/blob/main/image/IMG_001.PNG)
![](https://github.com/Jestain/PDF-to-EPUB-Converter/blob/main/image/IMG_002.PNG)
![](https://github.com/Jestain/PDF-to-EPUB-Converter/blob/main/image/IMG_003.PNG)<br>
**left**:PDF Document.**right**:Converted EPUB Document.
# Prerequisites
- Eclipse Version: 2021-12 (4.22.0)
- JDK Version: 1.8.0_202
- Tomcat Version: apache-tomcat-9.0.62
# Installation
- Clone this repo:
```
git clone https://github.com/Jestain/PDF-to-EPUB-Converter.git
cd PDF-to-EPUB-Converter
```
