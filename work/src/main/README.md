streaming class uses Google streaming recognition.
In order to over come the length limitation of audio stream. See https://cloud.google.com/speech/limits#content .
SingleUtterance in cofigure request is set to true and an audio streaming is started every time when require a sentence.
