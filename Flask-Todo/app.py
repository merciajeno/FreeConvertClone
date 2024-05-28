from flask import Flask, jsonify, request, send_file
from pdf2docx import Converter
from docx2pdf import convert
from io import BytesIO
import os
import moviepy.editor as mp
import comtypes.client

app = Flask(__name__)

def convert_pdf_doc(pdf_path,word_path):
    cv = Converter(pdf_path)
    cv.convert(word_path,start=0,end=None)
    cv.close()

@app.route('/PdfToWord',methods=['POST'])
def PdfToWord():
    if 'file' not in request.files:
        return "error"
    
    file = request.files['file']
    
    # If the user does not select a file, the browser submits an empty file without a filename
    if file.filename == '':
        return "error"
    
    if file:
        # Process the file (e.g., save it, convert it)
        # For demonstration, just return the filename
        print('File converted')
        print(file.content_type)
        pdf_bytes = BytesIO(file.read())
        word_bytes = BytesIO()

        with open("temp.pdf", "wb") as temp_pdf:
            temp_pdf.write(pdf_bytes.getbuffer())

        cv = Converter("temp.pdf")
        cv.convert("temp.docx", start=0, end=None)
        cv.close()

        with open("temp.docx", "rb") as temp_word:
            word_bytes.write(temp_word.read())

        word_bytes.seek(0)
        
        # Clean up temporary files
        os.remove("temp.pdf")
        #os.remove("temp.docx")
        output = os.path.abspath('temp.docx')
        return output

        

@app.route('/WordToPdf',methods=['POST'])
def WordToPdf():
    if 'file' not in request.files:
        return "error"
    comtypes.CoInitialize()
    word = request.files['file']
    bytes = BytesIO(word.read())

    with open('temp.docx','wb') as tmp:
        tmp.write(bytes.getbuffer())
    docx_path =os.path.abspath("temp.docx")
    dir = os.path.dirname("temp.docx")
    pdf_path = os.path.abspath(os.path.join(dir,"temp.pdf"))
    
    try:
        # Create a Word application object
        word = comtypes.client.CreateObject('Word.Application')
        word.Visible = False

        # Open the DOCX file
        doc = word.Documents.Open(docx_path)

        # Export as PDF
        doc.ExportAsFixedFormat(pdf_path, 17)  # 17 is the PDF format
        
        # Close the document
        doc.Close()
        word.Quit()
    except Exception as e:
        print(f"An error occurred: {e}")
    finally:
        comtypes.CoUninitialize()
    return pdf_path
    

@app.route('/VideoToAudio',methods=['POST'])
def VideoToAudio():
    if 'file' not in request.files: 
        return "error"

    video = request.files['file']
    dir = os.path.dirname('app.py')
    filename = video.filename
    path=os.path.abspath(os.path.join(dir,filename))
    video.save(path)
    video_clip = mp.VideoFileClip(filename)
    audio = video_clip.audio
    audio.write_audiofile('output.mp3')
    output =os.path.abspath('output.mp3')
    
    return output



if __name__=='__main__':
    app.run(debug=True)