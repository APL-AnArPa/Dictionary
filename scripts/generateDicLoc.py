import shutil

for i in range(5):
  with open('../Files/Dictionary'+str(i+1)+'.txt','w') as f:
    for j in range(5*pow(10,i+1)):
      f.write("%s " % str(j+1))

  shutil.copyfile('../Files/Dictionary'+str(i+1)+'.txt','../Files/Locate'+str(i+1)+'.txt');
