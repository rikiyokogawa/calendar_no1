import urllib.request, urllib.error
from bs4 import BeautifulSoup
import csv
from datetime import date

#IDの日付からyyyy/MM/ddをつくる
def convertDate(idDate,year):
    mm = idDate[4:6]
    dd = idDate[6:]
    return date(year,int(mm),int(dd)).strftime("%Y/%m/%d")

#取得元のURLを再指定する
def changeUrl(pageNumber,year):
    #pageNumberをStringに変換
    if pageNumber < 10:
        strPageNumber = "0" + str(pageNumber)
    else:
        strPageNumber =  str(pageNumber)
        
    url = "http://npb.jp/games/" + str(year) + "/schedule_" + strPageNumber + "_detail.html"
    html = urllib.request.urlopen(url)
    return BeautifulSoup(html, "html.parser")

def main(): 
    #取得する年(year) 取得期間の開始月(startMonth) 終了月(endMonth)を設定する
    year = 2020
    startMonth = 3
    endMonth = 11

    csvList = []

    # ファイルオープン
    f = open('baseballGames.csv','a')
    writer = csv.writer(f, lineterminator='\n')

    # 取得してくるURLを設定する
    # TODO:日本シリーズとかの所は取り方を考える
    # TODO:点数も取れるようになるかもしれない
    for pageNumber in range(startMonth,endMonth+1):
        soup = changeUrl(pageNumber,year)
        games = soup.find_all("tr")

        for game in games :
            # 試合が無い日は飛ばす(team1が無ければ飛ばす)
            gameCheck = game.find(class_="team1")
            try:
                if gameCheck is not None:
                    csvList.clear()
                    date = convertDate(game.get("id"),year)
                    csvList.append(date)
                    csvList.append(game.find(class_="team1").string)
                    csvList.append(game.find(class_="team2").string)
                    csvList.append(game.find(class_="place").string.replace("\u3000",""))
                    csvList.append(game.find(class_="time").string)

                    writer.writerow(csvList)
                    print(csvList)
            except:
                print(sys.exc_info())
                pass

    #ファイルクローズ
    f.close()


if __name__ == "__main__":
    main()

