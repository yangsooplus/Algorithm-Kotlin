# -*- coding:utf-8 -*-
import os
import subprocess

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
        for time, msg in recent_commit_files:
            readme_file.write(f"- {time} {msg}\n")

        readme_file.write("---\n")
        readme_file.write("## 지금까지 푼 문제\n\n")
        readme_file.write("|유형|개수|\n|---|---|\n")
        for folder, file_count in folder_data.items():
            readme_file.write(f"|`{folder}`|{file_count}|\n")


def get_recently_committed_files(limit=3):
    git_command = f'git log --pretty=format:"%ad:%s" --date=format:"%Y-%m-%d" -n {limit}'
    result = subprocess.run(git_command, shell=True, stdout=subprocess.PIPE, text=True)
    log_output = result.stdout.splitlines()
    
    commits = []
    for line in log_output:
        parts = line.strip().split(':')
        if len(parts) == 2:
            commit_time, commit_msg = parts
            commits.append((commit_time, commit_msg))
    
    return commits


if __name__ == "__main__":
    directory_to_scan = "solutions" 
    folder_data = count_files_in_folders(directory_to_scan)
    update_readme(folder_data, get_recently_committed_files())

