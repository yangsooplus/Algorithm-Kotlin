# -*- coding:utf-8 -*-
import os
import subprocess
import requests

def count_files_in_folders(directory):
    folder_data = {}
    for root, dirs, files in os.walk(directory):
        folder_name = os.path.basename(root)
        folder_data[folder_name] = len(files)
    del folder_data[directory]
    return folder_data


def update_readme(folder_data, recent_commit_files):
    with open("README.md", "w") as readme_file:
        readme_file.write("\n## 최근 푼 문제\n\n")
        for commit in recent_commit_files:
            readme_file.write(f"- {commit}\n")

        readme_file.write("---\n")
        readme_file.write("## 지금까지 푼 문제\n\n")
        readme_file.write("|유형|개수|\n|---|---|\n")
        for folder, file_count in folder_data.items():
            readme_file.write(f"|`{folder}`|{file_count}|\n")



def get_recent_commits_with_links(owner, repo, limit=10, token=None):
    # GitHub API 엔드포인트 URL 생성
    url = f"https://api.github.com/repos/{owner}/{repo}/commits?per_page={limit}"

    # 헤더에 토큰 추가 (토큰 없이는 public 리포지토리만 접근 가능)
    headers = {}
    if token:
        headers["Authorization"] = f"Bearer {token}"

    try:
        # GitHub API 호출
        response = requests.get(url, headers=headers)
        response.raise_for_status()

        # API 응답 JSON 파싱
        data = response.json()

        commits = []
        for commit in data:
            commit_message = commit["commit"]["message"]
            if "auto" in commit_message or ".kt" not in commit_message:
                continue
            commit_url = commit["html_url"]
            commit_date = commit["commit"]["committer"]["date"][:10]
            commits.append(f"{commit_date} [{commit_message}]({commit_url})")
            if len(commits) >= 3:
                break

        return commits
    except requests.exceptions.RequestException as e:
        print(f"GitHub API 호출 중 오류 발생: {e}")
        return None



if __name__ == "__main__":
    directory_to_scan = "solutions" 
    folder_data = count_files_in_folders(directory_to_scan)
    update_readme(folder_data, get_recent_commits_with_links("yangsooplus", "Algorithm-Kotlin"))

