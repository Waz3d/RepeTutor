-record(messages, {
  idSender,
  senderRole,
  idDest,
  message,
  banana
}).

-record(tutorNames, {
  idTutor,
  fullNameTutor
}).

-record(studentNames, {
  idStudent,
  fullNameStudent
}).

-record(pidStudentMap, {
  id, pid
}).

-record(pidTutorMap, {
  id, pid
}).