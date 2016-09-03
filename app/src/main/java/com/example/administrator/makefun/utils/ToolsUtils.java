package com.example.administrator.makefun.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class ToolsUtils {

	public static void openBrowser(Context context, String url) {
		try {
			Intent intent = new Intent();
			intent.setAction("android.intent.action.VIEW");
			url = url.trim();
			if (!url.startsWith("https://") && !url.startsWith("http://")) {
				url = "http://" + url;
			}
			Uri content_url = Uri.parse(url);
			intent.setData(content_url);
			context.startActivity(intent);
		} catch (Exception e) {
		}
	}

	/**
	 * 强制弹出输入法
	 * 
	 * @param txtSearchKey
	 * @param status
	 */
	public static void keyBoard(final EditText txtSearchKey, final int status) {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				InputMethodManager m = (InputMethodManager) txtSearchKey
						.getContext().getSystemService(
								Context.INPUT_METHOD_SERVICE);
				if (status == 0) {
					m.showSoftInput(txtSearchKey,
							InputMethodManager.SHOW_FORCED);
				} else {
					m.hideSoftInputFromWindow(txtSearchKey.getWindowToken(), 0);
				}
			}
		}, 300);
	}

	/**
	 * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
	 * 
	 * @param context
	 * @return true 表示开启
	 */
	public static final boolean isOPen(final Context context) {
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		// 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
		boolean gps = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		// 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
		// boolean network = locationManager
		// .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		// if (gps || network) {
		// return true;
		// }
		if (gps) {
			return true;
		}

		return false;
	}

	/**
	 * 检查手机上是否安装了指定的软件
	 * 
	 * @param context
	 * @param packageName
	 *            ：应用包名
	 * @return
	 */
	public static final boolean isAvilible(Context context, String packageName) {
		// 获取packagemanager
		final PackageManager packageManager = context.getPackageManager();
		// 获取所有已安装程序的包信息
		List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
		// 用于存储所有已安装程序的包名
		List<String> packageNames = new ArrayList<String>();
		// 从pinfo中将包名字逐一取出，压入pName list中
		if (packageInfos != null) {
			for (int i = 0; i < packageInfos.size(); i++) {
				String packName = packageInfos.get(i).packageName;
				System.out.println("--->" + packName);
				packageNames.add(packName);
			}
		}
		// 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
		return packageNames.contains(packageName);
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					State state = info[i].getState();
					if (state == State.CONNECTED
							|| state == State.CONNECTING) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 判断某个界面是否在前台
	 * 
	 * @param context
	 * @param className
	 *            某个界面名称
	 */
	public static boolean isForeground(Context context, String className) {
		if (context == null || StringUtils.isEmpty2(className)) {
			return false;
		}

		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> list = am.getRunningTasks(1);
		if (list != null && list.size() > 0) {
			ComponentName cpn = list.get(0).topActivity;
			if (className.equals(cpn.getClassName())) {
				return true;
			}
		}

		return false;
	}

	public static boolean isTopActivity(Context context, String packageName) {
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			// 应用程序位于堆栈的顶层
			if (packageName.equals(tasksInfo.get(0).topActivity
					.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 读文件
	 * 
	 * @param aFile
	 *            完整文件名(绝对路径)
	 * @return
	 */
	public static byte[] readFile(String aFile) {
		if (aFile == null) {
			return null;
		}

		File f = new File(aFile);
		InputStream out = null;
		byte[] buffer = null;
		try {
			out = new FileInputStream(f);
			// FIXME [mark] available方法对大文件有问题，因为它返回值为int，这里凑合先用
			int length = out.available();
			buffer = new byte[length];
			out.read(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return buffer;
	}

	public static boolean writeFile(String filePath, byte[] content) {
		if (null == filePath || null == content) {
			return false;
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			fos.write(content);
			fos.flush();
			fos.close();
			fos = null;
			return true;

		} catch (Exception ex) {
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (Exception ex) {
				}
				;
			}
		}
		return false;
	}

	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件不存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 追加文件：使用FileOutputStream，在构造FileOutputStream时，把第二个参数设为true
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void writeFileAdd(String file, String conent) {
		// BufferedWriter out = null;
		// try {
		// out = new BufferedWriter(new OutputStreamWriter(
		// new FileOutputStream(file, true)));
		// out.write(conent);
		// } catch (Exception e) {
		// e.printStackTrace();
		// } finally {
		// try {
		// out.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
	}

	public static boolean createDirectory(String filePath) {
		try {
			if (null == filePath) {
				return false;
			}
			File file = new File(filePath);

			if (file.exists()) {
				return true;
			}
			return file.mkdirs();
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}
}
