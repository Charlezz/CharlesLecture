package com.tistory.charlezz.camerasimply;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";

    private Button camera, gallery, crop;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.mImageView);

        camera = (Button) findViewById(R.id.camera);
        gallery = (Button) findViewById(R.id.gallery);
        crop = (Button) findViewById(R.id.crop);

        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);
        crop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //카메라
        if (v.equals(camera)) {
            takePhoto();
            return;
        }
        //갤러리
        if (v.equals(gallery)) {
            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, REQUEST_PHOTO_FROM_GALLERY);
            return;
        }
        //크롭
        if (v.equals(crop)) {
            if (mCurrentPhotoPath == null) {
                return;
            }
            Intent crop = new Intent("com.android.camera.action.CROP");
            crop.setDataAndType(Uri.fromFile(new File(mCurrentPhotoPath)), "image/*");

            // crop한 이미지를 저장할때 200x200 크기로 저장
            crop.putExtra("outputX", 200); // crop한 이미지의 x축 크기
            crop.putExtra("outputY", 200); // crop한 이미지의 y축 크기
            crop.putExtra("aspectX", 2); // crop 박스의 x축 비율
            crop.putExtra("aspectY", 1); // crop 박스의 y축 비율
            crop.putExtra("scale", true);
            crop.putExtra("return-data", true);
            startActivityForResult(crop, CROP);
        }
    }


    private String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // 이미지파일 이름 생성
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* 접두어 */
                ".jpg",         /* 접미어 */
                storageDir      /* 폴더 */
        );

        mCurrentPhotoPath = image.getAbsolutePath();
        Log.e(TAG, "mCurrentPhotoPath:" + mCurrentPhotoPath);
        return image;
    }

    //requestCode
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_PHOTO_FROM_GALLERY = 1;
    private static final int CROP = 2;

    //사진 찍기
    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 카메라를 다루는 액티비티가 있는지 확인
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // 사진이 저장될 장소의 파일 객체 만들기
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                //파일 만들기 실패
            }
            // 파일이 만들어졌을 때만 실행
            if (photoFile != null) {
                //인텐트에 extra값으로 아래와 같이 넣어줌
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private Bitmap bm = null;

    private void setPic() {
        // 이미지 뷰사이즈 계산
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        Log.e(TAG, "targetW:" + targetW + " targetH:" + targetH);

        // 비트맵 수치 계산
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        //true로 설정시 bitmap 객체 가져오지 않고 사이즈 가져옴
        bmOptions.inJustDecodeBounds = true;
        //파일명으로부터 비트맵 가져옴
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        Log.e(TAG, "photoW:" + photoW + " photoH:" + photoH);

        bmOptions.inJustDecodeBounds = false;
        if (photoW > targetW) {
            int scaleFactor = photoW / targetW + 1;
            bmOptions.inSampleSize = scaleFactor;
        }
        //롤리팝이후로는 deprecated
        bmOptions.inPurgeable = true;
        bm = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

        ExifInterface exif = null;
        try {
            exif = new ExifInterface(mCurrentPhotoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);
        bm = rotate(bm, exifDegree);
        mImageView.setImageBitmap(bm);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setPic();
            return;
        }

        if (requestCode == REQUEST_PHOTO_FROM_GALLERY && resultCode == RESULT_OK && null != data) {
            Uri uri = data.getData();
            mCurrentPhotoPath = getRealPathFromURI(uri);
            setPic();
            return;
        }
        if (requestCode == CROP && resultCode == RESULT_OK) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data"); // crop된 bitmap
                mImageView.setImageBitmap(photo);
            }
            return;
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        //콘텐트 리졸버를 통해 해당 URI에 관련된 것들을 조회
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public Bitmap rotate(Bitmap bitmap, int degrees) {
        if (degrees != 0 && bitmap != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);

            try {
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
                if (bitmap != converted) {
                    bitmap.recycle();
                    bitmap = converted;
                }
            } catch (OutOfMemoryError ex) {
                // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
            }
        }
        return bitmap;
    }

    public int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //비트맵에 할당 되었던 메모리 반환
        //안하면 메모리 누수
        bm.recycle();
    }
}
